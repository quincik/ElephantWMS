package com.elephant.wms.core.component.area;

import com.alibaba.fastjson2.JSONObject;
import com.elephant.wms.infrastructure.common.Result;
import com.elephant.wms.infrastructure.mapper.AreaMapper;
import com.elephant.wms.infrastructure.po.AreaPO;
import jakarta.annotation.Resource;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SimpleCreateArea implements Processor {

    @Resource
    private AreaMapper areaMapper;

    @Override
    public void process(Exchange exchange) throws Exception {

        List<AreaPO> entities = exchange.getMessage().getBody(List.class);

        int fail = 0;
        for (int i = 0; i < entities.size(); i++) {
            try {
                int count = areaMapper.insert(entities.get(i));
                if (1 != count) fail++;
            } catch (Throwable throwable) {
                fail++;
            }
        }

        if (fail != 0) {
            if (fail == entities.size()) {
                exchange.getMessage().setBody(new Result<>(false, "全部创建失败"));
                return;
            }
            exchange.getMessage().setBody(new Result<>(false, "有" + fail + "个库区创建失败"));
            return;
        }
        exchange.getMessage().setBody(new Result<>());

    }
}

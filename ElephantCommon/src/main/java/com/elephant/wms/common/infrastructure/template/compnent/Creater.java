package com.elephant.wms.common.infrastructure.template.compnent;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elephant.wms.common.infrastructure.object.Result;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class Creater<T> implements Processor {

    protected abstract BaseMapper getMapper();
    @Override
    @Transactional
    public void process(Exchange exchange) throws Exception {

        List<T> entities = (List<T>) exchange.getMessage().getBody(Result.class).getData();

        int fail = 0;
        for (int i = 0; i < entities.size(); i++) {
            try {
                int count = getMapper().insert(entities.get(i));
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
            exchange.getMessage().setBody(new Result<>(false, "有" + fail + "个创建失败"));
            return;
        }
        exchange.getMessage().setBody(new Result<>());
    }
}

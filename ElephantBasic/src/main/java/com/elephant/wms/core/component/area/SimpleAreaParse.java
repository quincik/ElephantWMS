package com.elephant.wms.core.component.area;

import com.alibaba.fastjson2.JSONObject;
import com.elephant.wms.infrastructure.po.AreaPO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SimpleAreaParse implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        Map<String, Object> param = exchange.getMessage().getBody(Map.class);
        String jsonStr = JSONObject.toJSONString(param);
        AreaPO entry = JSONObject.parseObject(jsonStr, AreaPO.class);
        exchange.getMessage().setBody(List.of(entry));
    }
}

package com.elephant.wms.common.infrastructure.template.compnent;

import com.alibaba.fastjson2.JSONObject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.List;
import java.util.Map;

public abstract class Parse <T> implements Processor {

    protected Class<T> type;

    @Override
    public void process(Exchange exchange) throws Exception {

        Map<String, Object> param = exchange.getMessage().getBody(Map.class);
        String jsonStr = JSONObject.toJSONString(param);
        T entry = JSONObject.parseObject(jsonStr, type);
        exchange.getMessage().setBody(List.of(entry));
    }

    protected abstract void init();
}

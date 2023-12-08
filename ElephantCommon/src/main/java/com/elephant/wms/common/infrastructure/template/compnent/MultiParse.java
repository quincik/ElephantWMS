package com.elephant.wms.common.infrastructure.template.compnent;

import com.alibaba.fastjson2.JSONObject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.List;
import java.util.Map;

public abstract class MultiParse<T> implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {

        Map<String, Object> param = exchange.getMessage().getBody(Map.class);
        String jsonStr = JSONObject.toJSONString(param);
        T entry = JSONObject.parseObject(jsonStr, getType());
        exchange.getMessage().setBody(List.of(entry));
    }

    protected abstract Class<T> getType();
}

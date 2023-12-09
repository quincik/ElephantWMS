package com.elephant.wms.common.infrastructure.template.compnent;

import com.alibaba.fastjson2.JSONObject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public abstract class SingerParse<T> implements Processor {

    protected Boolean supportMapParse(){
        return true;
    }

    protected void mapParse(Exchange exchange){
        Map<String, Object> param = exchange.getMessage().getBody(Map.class);
        String jsonStr = JSONObject.toJSONString(param);
        T entry = JSONObject.parseObject(jsonStr, getType());
        exchange.getMessage().setBody(entry);
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        if (supportMapParse()){
            try {
                mapParse(exchange);
            }catch (Exception e){
                // TODO log
            }
        }
    }

    protected abstract Type getType();
}

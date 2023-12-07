package com.elephant.wms.input.core.component.notice;

import com.alibaba.fastjson2.JSONObject;
import com.elephant.wms.common.infrastructure.template.compnent.Parse;
import com.elephant.wms.input.infrastructure.po.ReceiveNoticePO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SimpleReceiveNoticeParse implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        Map<String, Object> param = exchange.getMessage().getBody(Map.class);
        String jsonStr = JSONObject.toJSONString(param);
        ReceiveNoticePO entry = JSONObject.parseObject(jsonStr, ReceiveNoticePO.class);

        exchange.getMessage().setBody(entry);
    }
}

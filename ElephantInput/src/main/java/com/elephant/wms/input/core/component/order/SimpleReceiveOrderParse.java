package com.elephant.wms.input.core.component.order;

import com.alibaba.fastjson2.JSONObject;
import com.elephant.wms.input.infrastructure.po.ReceiveNoticeDetailPO;
import com.elephant.wms.input.infrastructure.po.ReceiveOrderPO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SimpleReceiveOrderParse implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        Map<String, Object> param = exchange.getMessage().getBody(Map.class);
        String jsonStr = JSONObject.toJSONString(param);
        ReceiveOrderPO entry = JSONObject.parseObject(jsonStr, ReceiveOrderPO.class);

        exchange.getMessage().setBody(entry);
    }
}

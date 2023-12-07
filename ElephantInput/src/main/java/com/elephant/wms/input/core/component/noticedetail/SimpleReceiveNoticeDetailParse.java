package com.elephant.wms.input.core.component.noticedetail;

import com.alibaba.fastjson2.JSONObject;
import com.elephant.wms.input.infrastructure.po.ReceiveNoticeDetailPO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SimpleReceiveNoticeDetailParse implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        Map<String, Object> param = exchange.getMessage().getBody(Map.class);
        String jsonStr = JSONObject.toJSONString(param);
        ReceiveNoticeDetailPO entry = JSONObject.parseObject(jsonStr, ReceiveNoticeDetailPO.class);

        exchange.getMessage().setBody(entry);
    }
}

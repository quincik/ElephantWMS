package com.elephant.wms.input.core.component.order;

import com.alibaba.fastjson2.JSONObject;
import com.elephant.wms.common.infrastructure.template.compnent.SingerParse;
import com.elephant.wms.input.infrastructure.po.ReceiveNoticeDetailPO;
import com.elephant.wms.input.infrastructure.po.ReceiveOrderPO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.Map;

@Component
public class SimpleReceiveOrderParse extends SingerParse<ReceiveOrderPO> {

    @Override
    protected Type getType() {
        return ReceiveOrderPO.class;
    }
}

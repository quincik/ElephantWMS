package com.elephant.wms.input.core.component.orderdetail;

import com.alibaba.fastjson2.JSONObject;
import com.elephant.wms.common.infrastructure.template.compnent.SingerParse;
import com.elephant.wms.input.infrastructure.po.ReceiveNoticeDetailPO;
import com.elephant.wms.input.infrastructure.po.ReceiveOrderDetailPO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.Map;

@Component
public class SimpleReceiveOrderDetailParse extends SingerParse<ReceiveOrderDetailPO> {


    @Override
    protected Type getType() {
        return ReceiveOrderDetailPO.class;
    }
}

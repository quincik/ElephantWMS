package com.elephant.wms.input.core.component.notice;

import com.alibaba.fastjson2.JSONObject;
import com.elephant.wms.common.infrastructure.template.compnent.SingerParse;
import com.elephant.wms.input.infrastructure.po.ReceiveNoticeDetailPO;
import com.elephant.wms.input.infrastructure.po.ReceiveNoticePO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.Map;

@Component
public class SimpleReceiveNoticeParse extends SingerParse<ReceiveNoticePO> {

    @Override
    protected Type getType() {
        return ReceiveNoticePO.class;
    }
}

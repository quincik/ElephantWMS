package com.elephant.wms.input.core.component.orderdetail.noticeReceive;

import com.elephant.wms.input.core.component.orderdetail.SimpleReceiveOrderDetailBuild;
import com.elephant.wms.input.infrastructure.po.ReceiveOrderDetailPO;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class NoticeReceiveOrderDetailBuild extends SimpleReceiveOrderDetailBuild {

    protected void buildUpdateNoticeExt(ReceiveOrderDetailPO entity,Exchange exchange){
        //todo 
    }

    @Override
    protected ReceiveOrderDetailPO buildExt(ReceiveOrderDetailPO entity, Exchange exchange){
        buildUpdateNoticeExt(entity,exchange);
        return super.buildExt(entity,exchange);
    }
}

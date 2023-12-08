package com.elephant.wms.input.core.component.orderdetail;

import com.elephant.wms.common.infrastructure.template.compnent.SingerBuild;
import com.elephant.wms.input.core.enums.ReceiveOrderStatus;
import com.elephant.wms.input.infrastructure.po.ReceiveOrderDetailPO;
import com.elephant.wms.input.infrastructure.po.ReceiveOrderPO;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import static com.elephant.wms.input.core.enums.ReceiveOrderStatus.RECEIVING;

@Component
public class SimpleReceiveOrderDetailBuild extends SingerBuild<ReceiveOrderDetailPO> {

    protected Boolean supportUpdateStandard(){
        return true;
    }
    protected Boolean supportUpdateOwner(){
        return true;
    }

    protected void buildUpdateOrderExt(ReceiveOrderDetailPO entity,Exchange exchange){

        ReceiveOrderPO receiveOrder = exchange.getMessage()
                .getHeader("receiveOrder", ReceiveOrderPO.class);
        ReceiveOrderDetailPO updateOrder = new ReceiveOrderDetailPO();
        updateOrder.setActualQuantity(receiveOrder.getActualQuantity() + entity.getActualQuantity());
        if(supportUpdateStandard())
            updateOrder.setStandard(entity.getStandard());
        if (supportUpdateOwner())
            updateOrder.setOwnerCode(entity.getOwnerCode());
        if(receiveOrder.getStatus() == ReceiveOrderStatus.CREATE.getCode())
            updateOrder.setStatus(RECEIVING.getCode());
        exchange.getMessage().setHeader("updateOrder",updateOrder);
    }

    @Override
    protected ReceiveOrderDetailPO build(ReceiveOrderDetailPO entity,Exchange exchange){
        //TODO detail status
        buildUpdateOrderExt(entity,exchange);
        return entity;
    }
}

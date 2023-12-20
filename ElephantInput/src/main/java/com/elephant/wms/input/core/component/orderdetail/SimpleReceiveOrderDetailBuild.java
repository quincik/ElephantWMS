package com.elephant.wms.input.core.component.orderdetail;

import com.elephant.wms.common.infrastructure.template.compnent.SingerBuild;
import com.elephant.wms.input.core.enums.ReceiveOrderStatus;
import com.elephant.wms.input.infrastructure.po.ReceiveOrderDetailPO;
import com.elephant.wms.input.infrastructure.po.ReceiveOrderPO;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static com.elephant.wms.input.core.enums.ReceiveOrderStatus.RECEIVING;

@Component
public class SimpleReceiveOrderDetailBuild extends SingerBuild<ReceiveOrderDetailPO> {

    protected Boolean needUpdateOrderStandard(){
        return true;
    }
    protected Boolean needUpdateOrderOwner(){
        return true;
    }

    protected void buildUpdateOrderExt(ReceiveOrderDetailPO entity,Exchange exchange){

        ReceiveOrderPO receiveOrder = exchange.getMessage()
                .getHeader("receiveOrder", ReceiveOrderPO.class);
        ReceiveOrderPO updateOrder = new ReceiveOrderPO();
        updateOrder.setActualQuantity(receiveOrder.getActualQuantity() + entity.getActualQuantity());
        if(needUpdateOrderStandard())
            updateOrder.setStandard(entity.getStandard());
        if (needUpdateOrderOwner())
            updateOrder.setOwnerCode(entity.getOwnerCode());
        if(receiveOrder.getStatus() == ReceiveOrderStatus.CREATE.getCode())
            updateOrder.setStatus(RECEIVING.getCode());
        exchange.getMessage().setHeader("updateOrder",updateOrder);
    }

    protected void buildItemBatchExt(ReceiveOrderDetailPO entity,Exchange exchange){

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        ReceiveOrderPO receiveOrder = exchange.getMessage()
                .getHeader("receiveOrder", ReceiveOrderPO.class);
        Map<String, Object> itemBatchParam = new HashMap<>();
        itemBatchParam.put("itemCode",entity.getItemCode());
        itemBatchParam.put("ownerCode",entity.getOwnerCode());
        itemBatchParam.put("manufacturingDate",currentDateTime.format(formatter) );
        exchange.getMessage().setHeader("itemBatchParam",itemBatchParam);
    }

    @Override
    protected ReceiveOrderDetailPO buildExt(ReceiveOrderDetailPO entity,Exchange exchange){
        //TODO detail status
        buildUpdateOrderExt(entity,exchange);
        buildItemBatchExt(entity,exchange);
        return entity;
    }
}

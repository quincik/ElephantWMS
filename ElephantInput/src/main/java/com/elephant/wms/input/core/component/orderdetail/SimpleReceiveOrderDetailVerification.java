package com.elephant.wms.input.core.component.orderdetail;

import com.elephant.wms.basic.interfaces.service.ItemService;
import com.elephant.wms.common.infrastructure.template.compnent.SingerVerification;
import com.elephant.wms.input.core.domain.ReceiveBasicQuery;
import com.elephant.wms.input.core.enums.ReceiveOrderStatus;
import com.elephant.wms.input.infrastructure.po.ReceiveOrderDetailPO;
import com.elephant.wms.input.infrastructure.po.ReceiveOrderPO;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import org.apache.camel.Exchange;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static com.elephant.wms.common.infrastructure.enums.INPUT_ERROR.*;

@Component
public class SimpleReceiveOrderDetailVerification extends SingerVerification<ReceiveOrderDetailPO> {

    @Resource
    ItemService itemService;

    protected @Nonnull String verifiedOwnerExt(ReceiveOrderPO order,
                                               ReceiveOrderDetailPO detail){
        if( null != order.getOwnerCode() &&
                !order.getOwnerCode().equals(detail.getOwnerCode()) ){
            return IN01B111001.getDesc();
        }
        return Strings.EMPTY;
    }

    protected @Nonnull String verifiedOrderExt(ReceiveOrderPO order){
        if(null == order){
            return IN01B111002.getDesc();
        }
        if(order.getStatus().equals(ReceiveOrderStatus.COMPLETE.getCode())){
            return IN01B111003.getDesc();
        }
        return  Strings.EMPTY;
    }

    /**
     * 基础校验，不建议扩展
     * @param entity
     * @return
     */
    protected @Nonnull List<String> verifiedEntity(ReceiveOrderDetailPO entity){

        List<String> result = new LinkedList<>();
        if(null == entity.getActualQuantity() || entity.getActualQuantity() < 1){
            result.add(IN01B110001.getDesc());
        }
        return result;
    }


    protected @Nonnull List<String> verified(ReceiveOrderDetailPO entity, Exchange exchange) {

        List<String> result = verifiedEntity(entity);

        ReceiveOrderPO receiveOrderPO = ReceiveBasicQuery.getOrder(entity.getReceiveOrderCode());
        verifiedExt(result,verifiedOrderExt(receiveOrderPO));
        exchange.getMessage().setHeader("receiveOrder",receiveOrderPO);

        verifiedExt(result,verifiedOwnerExt(receiveOrderPO,entity));

        Optional<ItemService.ItemDTO> itemDTO =
                itemService.queryByCode(entity.getItemCode(),entity.getOwnerCode());
        verifiedExt(result, isEmpty(itemDTO,IN00B0000001.getDesc()));

        return result;
    }

    @Override
    protected Class<ReceiveOrderDetailPO> getType() {
        return ReceiveOrderDetailPO.class;
    }


}

package com.elephant.wms.core.component;

import com.elephant.wms.core.bo.ModifyInventoryBO;
import com.elephant.wms.infrastructure.mapper.InventoryDetailMapper;
import com.elephant.wms.infrastructure.object.Result;
import com.elephant.wms.infrastructure.po.InventoryDetailPO;
import jakarta.annotation.Resource;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SimpleModifyDetail implements Processor {

    @Resource
    InventoryDetailMapper inventoryDetailMapper;

    @Override
    @Transactional
    public void process(Exchange exchange) throws Exception {

        ModifyInventoryBO modify = exchange.getMessage()
                .getHeader("modify",ModifyInventoryBO.class);

        InventoryDetailPO detail = exchange.getMessage().getBody(InventoryDetailPO.class);
        int count = inventoryDetailMapper.modifyInventory(modify.getAmount(), detail);
        if( 0 == count) {
            exchange.getMessage().setBody(new Result<InventoryDetailPO>(false,"调整库存失败。"));
            return;
        }
        detail.setAvailableQuantity(detail.getAvailableQuantity() + modify.getAmount());
        exchange.getMessage().setBody(new Result<>(detail));

    }
}

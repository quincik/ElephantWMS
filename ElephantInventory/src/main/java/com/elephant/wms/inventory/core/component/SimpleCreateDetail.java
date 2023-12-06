package com.elephant.wms.inventory.core.component;

import com.elephant.wms.inventory.core.bo.ModifyInventoryBO;
import com.elephant.wms.inventory.infrastructure.mapper.InventoryDetailMapper;
import com.elephant.wms.common.infrastructure.object.Result;
import com.elephant.wms.inventory.infrastructure.mapper.InventoryIdempotentMapper;
import com.elephant.wms.inventory.infrastructure.po.InventoryDetailPO;
import com.elephant.wms.inventory.infrastructure.po.InventoryIdempotentPO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SimpleCreateDetail  implements Processor {

    @Resource
    InventoryDetailMapper inventoryDetailMapper;
    @Resource
    InventoryIdempotentMapper inventoryIdempotentMapper;
    @Resource
    ObjectMapper objectMapper;

    @Override
    @Transactional
    public void process(Exchange exchange) throws Exception {

        ModifyInventoryBO modify = exchange.getMessage()
                .getHeader("modify",ModifyInventoryBO.class);
        InventoryDetailPO detail = exchange.getMessage().getBody(InventoryDetailPO.class);
        if(null == detail) {
            exchange.getMessage().setBody(new Result<>(false,"无明细，构建库存明细失败"));
            return;
        }
        int count = inventoryDetailMapper.insert(detail);
        if( 0 == count){
            exchange.getMessage().setBody(new Result<>(false,"构建库存明细失败"));
            return;
        }

        InventoryIdempotentPO idempotent = modify.toInventoryIdempotentPO();
        idempotent.setExtend(objectMapper.valueToTree(modify).toString());
        inventoryIdempotentMapper.insert(idempotent);
        exchange.getMessage().setBody(new Result<>());
    }
}

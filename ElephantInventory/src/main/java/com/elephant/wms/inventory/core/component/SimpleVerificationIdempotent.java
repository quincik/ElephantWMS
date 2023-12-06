package com.elephant.wms.inventory.core.component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elephant.wms.inventory.core.bo.ModifyInventoryBO;
import com.elephant.wms.common.infrastructure.object.Result;
import com.elephant.wms.inventory.infrastructure.mapper.InventoryIdempotentMapper;
import com.elephant.wms.inventory.infrastructure.po.InventoryIdempotentPO;
import jakarta.annotation.Resource;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class SimpleVerificationIdempotent implements Processor {

    @Resource
    InventoryIdempotentMapper inventoryIdempotentMapper;

    @Override
    public void process(Exchange exchange) throws Exception {

        ModifyInventoryBO modify = exchange.getMessage()
                .getHeader("modify",ModifyInventoryBO.class);

        QueryWrapper<InventoryIdempotentPO> query = new QueryWrapper<>(modify.toInventoryIdempotentPO());
        InventoryIdempotentPO idempotentPO = inventoryIdempotentMapper.selectOne(query);
        if(null != idempotentPO){
            exchange.getMessage().setHeader("idempotent",true);
            exchange.getMessage().setBody(new Result<>(true,true,"操作库存幂等。"));
            return;
        }
        exchange.getMessage().setHeader("idempotent",false);
        exchange.getMessage().setBody(new Result<>(true,false,""));
    }
}

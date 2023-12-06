package com.elephant.wms.inventory.core.component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elephant.wms.inventory.core.bo.ModifyInventoryBO;
import com.elephant.wms.inventory.infrastructure.mapper.InventoryDetailMapper;
import com.elephant.wms.inventory.infrastructure.po.InventoryDetailPO;
import jakarta.annotation.Resource;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class SimpleQueryDetail implements Processor {

    @Resource
    InventoryDetailMapper inventoryDetailMapper;

    @Override
    public void process(Exchange exchange) throws Exception {

        ModifyInventoryBO modify = exchange.getMessage()
                .getHeader("modify",ModifyInventoryBO.class);

        QueryWrapper<InventoryDetailPO> query = new QueryWrapper<>();
        query.eq("item_batch",modify.getItemBatch());
        query.eq("storage_code",modify.getStorageCode());

        InventoryDetailPO detail = inventoryDetailMapper.selectOne(query);
        exchange.getMessage().setBody(detail);

    }
}

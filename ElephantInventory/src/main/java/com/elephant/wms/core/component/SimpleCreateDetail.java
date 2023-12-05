package com.elephant.wms.core.component;

import com.elephant.wms.infrastructure.mapper.InventoryDetailMapper;
import com.elephant.wms.infrastructure.object.Result;
import com.elephant.wms.infrastructure.po.InventoryDetailPO;
import jakarta.annotation.Resource;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SimpleCreateDetail  implements Processor {

    @Resource
    InventoryDetailMapper inventoryDetailMapper;

    @Override
    @Transactional
    public void process(Exchange exchange) throws Exception {

        InventoryDetailPO detail = exchange.getMessage().getBody(InventoryDetailPO.class);
        if(null == detail) {
            exchange.getMessage().setBody(new Result<>(false,"构建库存明细失败"));
            return;
        }
        int count = inventoryDetailMapper.insert(detail);
        if( 0 == count){
            exchange.getMessage().setBody(new Result<>(false,"插入库存明细失败"));
            return;
        }
        exchange.getMessage().setBody(new Result<>(detail));

    }
}

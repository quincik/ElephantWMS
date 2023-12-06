package com.elephant.wms.inventory.core.component.scenario;

import com.elephant.wms.common.infrastructure.object.Result;
import com.elephant.wms.basic.interfaces.service.ItemBatchService;
import com.elephant.wms.basic.interfaces.service.StorageService;
import com.elephant.wms.inventory.core.bo.ModifyInventoryBO;
import jakarta.annotation.Resource;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReceivingRule implements Processor {

    @Resource
    ItemBatchService itemBatchService;

    @Resource
    StorageService storageService;

    @Override
    public void process(Exchange exchange) throws Exception {

        ModifyInventoryBO modify = exchange.getMessage()
                .getHeader("modify",ModifyInventoryBO.class);

        Optional<ItemBatchService.ItemBatchDTO> itemBatch = itemBatchService.queryById(modify.getItemBatch());
        if(itemBatch.isEmpty()) {
            exchange.getMessage().setBody(new Result<>(false,modify.getItemBatch() + "商品批次无法查询。"));
            return;
        }

        Optional<StorageService.StorageDTO> storage = storageService.queryByCode(modify.getStorageCode());
        if(storage.isEmpty()) {
            exchange.getMessage().setBody(new Result<>(false,modify.getStorageCode() + "货位信息无法查询。"));
            return;
        }

        //TODO 收货入库业务规则

        exchange.getMessage().setHeader("storage",storage);
        exchange.getMessage().setHeader("itemBatch",itemBatch);
        exchange.getMessage().setBody(new Result<>());
    }
}

package com.elephant.wms.core.component;

import com.elephant.wms.core.bo.ModifyInventoryBO;
import com.elephant.wms.infrastructure.po.InventoryDetailPO;
import com.elephant.wms.interfaces.service.ItemBatchService;
import com.elephant.wms.interfaces.service.StorageService;
import jakarta.annotation.Resource;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SimpleBuildDetail  implements Processor {

    @Resource
    ItemBatchService itemBatchService;

    @Resource
    StorageService storageService;

    private  InventoryDetailPO getInventoryDetailPO(ModifyInventoryBO modify,
                                                    Optional<ItemBatchService.ItemBatchDTO> itemBatch,
                                                    Optional<StorageService.StorageDTO> storage) {
        InventoryDetailPO entity = new InventoryDetailPO();
        entity.setItemBatch(itemBatch.get().getId());
        entity.setItemCode(itemBatch.get().getItemCode());
        entity.setOwnerCode(itemBatch.get().getOwnerCode());

        //TODO type logic.
        entity.setType(0);

        entity.setStorageCode(storage.get().getCode());
        entity.setAreaCode(storage.get().getAreaCode());

        entity.setAvailableQuantity(modify.getAmount());
        entity.setFrozenQuantity(0);
        return entity;
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        ModifyInventoryBO modify = exchange.getMessage()
                .getHeader("modify",ModifyInventoryBO.class);
        Optional<ItemBatchService.ItemBatchDTO> itemBatch = itemBatchService.queryById(modify.getItemBatch());
        if(itemBatch.isEmpty()) return;
        Optional<StorageService.StorageDTO> storage = storageService.queryByCode(modify.getStorageCode());
        if(storage.isEmpty()) return;

        InventoryDetailPO entity = getInventoryDetailPO(modify, itemBatch, storage);
        exchange.getMessage().setBody(entity);
    }

}

package com.elephant.wms.inventory.interfaces.service;

import com.elephant.wms.common.infrastructure.object.Result;
import lombok.Data;

public interface InventoryService {
    @Data
    public static class ModifyInventoryParam{

        private Integer amount;
        private Long itemBatch;
        private String storageCode;

        private String referenceCode;
        private String idempotentKey;
        private Integer scenario;

    }

    public Result<Boolean> modifyInventory(ModifyInventoryParam modify);

}

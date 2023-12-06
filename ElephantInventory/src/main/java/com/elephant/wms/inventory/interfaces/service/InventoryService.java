package com.elephant.wms.interfaces.service;

import com.elephant.wms.core.bo.ModifyInventoryBO;
import com.elephant.wms.core.enums.OptEnum;
import com.elephant.wms.infrastructure.object.Result;
import com.elephant.wms.infrastructure.po.InventoryDetailPO;
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

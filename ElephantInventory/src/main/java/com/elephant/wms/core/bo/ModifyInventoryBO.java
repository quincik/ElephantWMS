package com.elephant.wms.core.bo;

import com.elephant.wms.core.enums.InventoryOptEnum;
import lombok.Data;

@Data
public class ModifyInventoryBO {

    private Integer amount;
    private Long itemBatch;
    private String storageCode;

    private InventoryOptEnum operation;

    private String referenceCode;
    private String idempotentKey;

}

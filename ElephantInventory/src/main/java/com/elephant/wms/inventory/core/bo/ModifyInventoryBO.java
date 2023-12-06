package com.elephant.wms.core.bo;

import com.elephant.wms.core.enums.OptEnum;
import lombok.Data;

@Data
public class ModifyInventoryBO {

    private Integer amount;
    private Long itemBatch;
    private String storageCode;

    private OptEnum operation;

    private String referenceCode;
    private String idempotentKey;

    private Integer scenario;

}

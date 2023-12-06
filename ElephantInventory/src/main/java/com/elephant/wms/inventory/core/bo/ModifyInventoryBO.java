package com.elephant.wms.inventory.core.bo;

import ch.qos.logback.classic.sift.ContextBasedDiscriminator;
import com.elephant.wms.inventory.core.enums.OptEnum;
import com.elephant.wms.inventory.core.enums.ScenarioEnum;
import com.elephant.wms.inventory.infrastructure.po.InventoryIdempotentPO;
import lombok.Data;

@Data
public class ModifyInventoryBO {

    private Integer amount;
    private Long itemBatch;
    private String storageCode;

    private String referenceCode;
    private String idempotentKey;

    private ScenarioEnum scenario;

    public InventoryIdempotentPO toInventoryIdempotentPO(){
        InventoryIdempotentPO idempotent = new InventoryIdempotentPO();
        idempotent.setKeyword(this.getIdempotentKey());
        idempotent.setScenario(this.getScenario().getCode());
        idempotent.setReferenceCode(this.getReferenceCode());
        return idempotent;
    }

}

package com.elephant.wms.inventory.interfaces.rest;

import com.elephant.wms.inventory.core.bo.ModifyInventoryBO;
import com.elephant.wms.inventory.core.domain.ModifyInventoryService;
import com.elephant.wms.inventory.core.enums.OptEnum;
import com.elephant.wms.common.infrastructure.object.Result;
import com.elephant.wms.inventory.core.enums.ScenarioEnum;
import com.elephant.wms.inventory.interfaces.service.InventoryService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/inventory/details")
public class InventoryDetailRest {

    @Resource
    InventoryService inventoryService;

    @PostMapping("/query")
    public Result<Boolean> query(@RequestBody Map<String, Object> param){

        InventoryService.ModifyInventoryParam modify = new InventoryService.ModifyInventoryParam();
        modify.setAmount(1000);
        modify.setItemBatch(1l);
        modify.setStorageCode("A01-01-01");
        modify.setIdempotentKey("MD938489395");
        modify.setReferenceCode("ASN202938493");
        modify.setScenario(10);

        inventoryService.modifyInventory(modify);
        return new Result<>();
    }

}

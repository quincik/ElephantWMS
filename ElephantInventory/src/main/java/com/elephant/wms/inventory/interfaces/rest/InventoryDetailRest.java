package com.elephant.wms.interfaces.rest;

import com.elephant.wms.core.bo.ModifyInventoryBO;
import com.elephant.wms.core.domain.ModifyInventoryService;
import com.elephant.wms.core.enums.OptEnum;
import com.elephant.wms.infrastructure.object.Result;
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
    ModifyInventoryService modifyInventoryService;

    @PostMapping("/query")
    public Result<Boolean> query(@RequestBody Map<String, Object> param){

        ModifyInventoryBO modify = new ModifyInventoryBO();
        modify.setAmount(1000);
        modify.setItemBatch(1l);
        modify.setStorageCode("A01-01-01");
        modify.setOperation(OptEnum.ADJUST);
        modify.setIdempotentKey("MD938489393");
        modify.setReferenceCode("ASN202938493");

        modifyInventoryService.modifyInventory(modify);
        return new Result<>();
    }

}

package com.elephant.wms.inventory.interfaces.rest;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.elephant.wms.common.infrastructure.object.Result;
import com.elephant.wms.common.infrastructure.template.rest.BasicRest;
import com.elephant.wms.inventory.infrastructure.mapper.InventoryDetailMapper;
import com.elephant.wms.inventory.infrastructure.po.InventoryDetailPO;
import com.elephant.wms.inventory.interfaces.rest.convert.RestConvert;
import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/inventory/details")
public class InventoryDetailRest extends BasicRest {

    @Override
    protected BaseMapper getMapper() {
        return inventoryDetailMapper;
    }

    @Data
    public static class InventoryDetailVO{

        private Long id;

        private String createTime;
        private String modifyTime;

        private Long itemBatch;
        private String itemCode;
        private Integer type;
        private String ownerCode;
        private String storageCode;
        private String areaCode;
        private Integer availableQuantity;
        private Integer frozenQuantity;
        private Integer standard;
    }

    @Resource
    InventoryDetailMapper inventoryDetailMapper;

    @PostMapping("/query")
    public Result<List<InventoryDetailVO>> query(@RequestBody Map<String, Object> param){

        IPage<InventoryDetailPO> inventoryDetails = super.query(param, InventoryDetailPO.class);
        Result<List<InventoryDetailVO>> result = new Result<>(inventoryDetails.getCurrent(),inventoryDetails.getSize(),inventoryDetails.getTotal());
        result.setData(RestConvert.INSTANCE.toInventoryDetailsVO(inventoryDetails.getRecords()));

        return  result;
    }

}

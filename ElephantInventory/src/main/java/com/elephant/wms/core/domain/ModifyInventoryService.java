package com.elephant.wms.core.domain;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elephant.wms.core.bo.ModifyInventoryBO;
import com.elephant.wms.core.enums.InventoryOptEnum;
import com.elephant.wms.infrastructure.mapper.InventoryDetailMapper;
import com.elephant.wms.infrastructure.object.Result;
import com.elephant.wms.infrastructure.po.InventoryDetailPO;
import com.elephant.wms.interfaces.service.ItemBatchService;
import com.elephant.wms.interfaces.service.StorageService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class ModifyInventoryService {

    @Resource
    InventoryDetailMapper inventoryDetailMapper;

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

        entity.setQuality(modify.getQuality());
        entity.setAvailableQuantity(modify.getAmount());
        entity.setFrozenQuantity(0);
        return entity;
    }

    private InventoryDetailPO addDetail(ModifyInventoryBO modify){

        Optional<ItemBatchService.ItemBatchDTO> itemBatch = itemBatchService.queryById(modify.getItemBatch());
        if(itemBatch.isEmpty()) return null;
        Optional<StorageService.StorageDTO> storage = storageService.queryByCode(modify.getStorageCode());
        if(storage.isEmpty()) return null;

        InventoryDetailPO entity = getInventoryDetailPO(modify, itemBatch, storage);
        int count = inventoryDetailMapper.insert(entity);
        if( 0 == count)
            return null;
        return entity;
    }



    private InventoryDetailPO modifyDetail(ModifyInventoryBO modify){

        QueryWrapper<InventoryDetailPO> query = new QueryWrapper<>();
        query.eq("itemBatch",modify.getItemBatch());
        query.eq("storageCode",modify.getItemBatch());
        query.eq("quality",modify.getItemBatch());

        InventoryDetailPO detail = inventoryDetailMapper.selectOne(query);
        if(modify.getOperation().equals(InventoryOptEnum.FREEZE)) {
            if(null == detail) return null;
            if( 0 == inventoryDetailMapper.freezeInventory(modify.getAmount(), detail))
                return null;
            return detail;
        }

        if(null == detail)
            return addDetail(modify);
        if( 0 == inventoryDetailMapper.modifyInventory(modify.getAmount(), detail))
            return null;
        return detail;
    }

    @Transactional
    public Result<Integer> modifyInventory(ModifyInventoryBO modify){

        InventoryDetailPO detail = modifyDetail(modify);
        if( null == detail ) return new Result<>(0);

        return new Result<>();
    }
}

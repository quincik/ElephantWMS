package com.elephant.wms.interfaces.rest.convert;


import com.elephant.wms.infrastructure.po.*;
import com.elephant.wms.interfaces.rest.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface Convert {
    Convert INSTANCE = Mappers.getMapper(Convert.class);
    List<AreaRest.AreaVO> toAreaVO(List<AreaPO> areaPO);

    List<StorageRest.StorageVO> toStorageVO(List<StoragePO> records);

    List<ItemRest.ItemVO> toItemVO(List<ItemPO> records);

    List<OwnerRest.OwnerVO> toOwnerVO(List<OwnerPO> records);

    List<ItemBatchRest.ItemBatchVO> toItemBatchVO(List<ItemBatchPO> records);
}
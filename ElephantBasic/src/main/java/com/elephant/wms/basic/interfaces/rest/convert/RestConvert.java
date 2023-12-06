package com.elephant.wms.basic.interfaces.rest.convert;


import com.elephant.wms.basic.infrastructure.po.*;
import com.elephant.wms.basic.interfaces.rest.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RestConvert {
    RestConvert INSTANCE = Mappers.getMapper(RestConvert.class);
    List<AreaRest.AreaVO> toAreaVO(List<AreaPO> records);

    List<StorageRest.StorageVO> toStorageVO(List<StoragePO> records);

    List<ItemRest.ItemVO> toItemVO(List<ItemPO> records);

    List<OwnerRest.OwnerVO> toOwnerVO(List<OwnerPO> records);

    List<ItemBatchRest.ItemBatchVO> toItemBatchVO(List<ItemBatchPO> records);
}
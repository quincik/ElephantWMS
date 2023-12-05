package com.elephant.wms.interfaces.service.convert;

import com.elephant.wms.infrastructure.po.ItemBatchPO;
import com.elephant.wms.infrastructure.po.StoragePO;
import com.elephant.wms.interfaces.service.ItemBatchService;
import com.elephant.wms.interfaces.service.StorageService;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ServiceConvert {

    ServiceConvert INSTANCE = Mappers.getMapper(ServiceConvert.class);

    ItemBatchService.ItemBatchDTO toItemBatchDTO(ItemBatchPO itemBatch);

    StorageService.StorageDTO toStorageDTO(StoragePO entity);
}

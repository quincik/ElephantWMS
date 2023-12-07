package com.elephant.wms.basic.interfaces.service.convert;

import com.elephant.wms.basic.infrastructure.po.ItemPO;
import com.elephant.wms.basic.infrastructure.po.OwnerPO;
import com.elephant.wms.basic.interfaces.service.ItemBatchService;
import com.elephant.wms.basic.interfaces.service.ItemService;
import com.elephant.wms.basic.interfaces.service.OwnerService;
import com.elephant.wms.basic.interfaces.service.StorageService;
import com.elephant.wms.basic.infrastructure.po.ItemBatchPO;
import com.elephant.wms.basic.infrastructure.po.StoragePO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ServiceConvert {

    ServiceConvert INSTANCE = Mappers.getMapper(ServiceConvert.class);

    ItemBatchService.ItemBatchDTO toItemBatchDTO(ItemBatchPO itemBatch);

    StorageService.StorageDTO toStorageDTO(StoragePO entity);

    OwnerService.OwnerDTO toOwnerDTO(OwnerPO entity);


    ItemService.ItemDTO toItemDTO(ItemPO itemPO);
}

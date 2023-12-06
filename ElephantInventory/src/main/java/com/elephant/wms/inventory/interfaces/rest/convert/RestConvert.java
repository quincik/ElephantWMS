package com.elephant.wms.inventory.interfaces.rest.convert;

import com.elephant.wms.inventory.infrastructure.po.InventoryDetailPO;
import com.elephant.wms.inventory.interfaces.rest.InventoryDetailRest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RestConvert {
    RestConvert INSTANCE = Mappers.getMapper(RestConvert.class);


    List<InventoryDetailRest.InventoryDetailVO> toInventoryDetailsVO(List<InventoryDetailPO> records);
}

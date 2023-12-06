package com.elephant.wms.inventory.interfaces.service.convert;

import com.elephant.wms.basic.infrastructure.po.AreaPO;
import com.elephant.wms.basic.interfaces.rest.AreaRest;
import com.elephant.wms.inventory.core.bo.ModifyInventoryBO;
import com.elephant.wms.inventory.interfaces.service.InventoryService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ServiceConvert {
    ServiceConvert INSTANCE = Mappers.getMapper(ServiceConvert.class);

    @Mapping(target = "scenario", ignore = true)
    ModifyInventoryBO toModifyInventoryBO(InventoryService.ModifyInventoryParam param);
}

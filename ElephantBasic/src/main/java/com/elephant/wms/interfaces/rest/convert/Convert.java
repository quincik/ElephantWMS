package com.elephant.wms.interfaces.rest.convert;


import com.elephant.wms.infrastructure.po.AreaPO;
import com.elephant.wms.interfaces.rest.AreaRest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface Convert {
    Convert INSTANCE = Mappers.getMapper(Convert.class);
    List<AreaRest.AreaVO> toVO(List<AreaPO> areaPO);
}
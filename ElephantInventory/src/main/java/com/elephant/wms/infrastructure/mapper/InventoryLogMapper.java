package com.elephant.wms.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elephant.wms.infrastructure.po.InventoryDetailPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InventoryLogMapper extends BaseMapper<InventoryDetailPO> {

}

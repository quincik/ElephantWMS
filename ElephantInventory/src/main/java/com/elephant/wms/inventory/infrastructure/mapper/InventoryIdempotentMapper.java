package com.elephant.wms.inventory.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elephant.wms.inventory.infrastructure.po.InventoryIdempotentPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InventoryIdempotentMapper extends BaseMapper<InventoryIdempotentPO> {
}

package com.elephant.wms.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elephant.wms.infrastructure.po.InventoryDetailPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface InventoryDetailMapper extends BaseMapper<InventoryDetailPO> {

    public int freezeInventory(@Param("frozenIncrement") Integer frozenIncrement,
                               @Param("inventory") InventoryDetailPO inventory);

    public int modifyInventory(@Param("increment") Integer increment,
                               @Param("inventory") InventoryDetailPO inventory);
}

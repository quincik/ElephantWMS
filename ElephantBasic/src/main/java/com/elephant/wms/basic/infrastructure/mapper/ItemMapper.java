package com.elephant.wms.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elephant.wms.infrastructure.po.ItemPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ItemMapper extends BaseMapper<ItemPO> {
}
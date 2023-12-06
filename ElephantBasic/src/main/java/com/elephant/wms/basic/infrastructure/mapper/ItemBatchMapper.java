package com.elephant.wms.basic.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elephant.wms.basic.infrastructure.po.ItemBatchPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ItemBatchMapper extends BaseMapper<ItemBatchPO> {

}

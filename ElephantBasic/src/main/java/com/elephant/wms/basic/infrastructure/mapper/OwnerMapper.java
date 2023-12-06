package com.elephant.wms.basic.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elephant.wms.basic.infrastructure.po.OwnerPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OwnerMapper extends BaseMapper<OwnerPO> {
}

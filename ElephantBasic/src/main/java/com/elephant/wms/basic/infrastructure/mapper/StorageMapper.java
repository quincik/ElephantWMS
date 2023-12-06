package com.elephant.wms.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elephant.wms.infrastructure.po.StoragePO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StorageMapper extends BaseMapper<StoragePO> {

}

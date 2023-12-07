package com.elephant.wms.input.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elephant.wms.input.infrastructure.po.ReceiveOrderDetailPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReceiveOrderDetailMapper extends BaseMapper<ReceiveOrderDetailPO> {
}

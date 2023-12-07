package com.elephant.wms.input.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elephant.wms.input.infrastructure.po.ReceiveOrderPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReceiveOrderMapper extends BaseMapper<ReceiveOrderPO> {

    int insertCondition( @Param("order") ReceiveOrderPO receiveOrderPO);
}

package com.elephant.wms.input.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elephant.wms.input.infrastructure.po.ReceiveNoticePO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReceiveNoticeMapper extends BaseMapper<ReceiveNoticePO> {
}

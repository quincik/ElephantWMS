package com.elephant.wms.input.core.component.notice;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elephant.wms.common.infrastructure.template.compnent.Creater;
import com.elephant.wms.input.infrastructure.mapper.ReceiveNoticeMapper;
import com.elephant.wms.input.infrastructure.po.ReceiveNoticePO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class SimpleReceiveNoticeCreate extends Creater<ReceiveNoticePO> {

    @Resource
    private ReceiveNoticeMapper receiveNoticeMapper;

    @Override
    protected BaseMapper getMapper() {
        return receiveNoticeMapper;
    }

}

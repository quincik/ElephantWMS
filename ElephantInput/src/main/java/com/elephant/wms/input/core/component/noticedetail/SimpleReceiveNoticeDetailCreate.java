package com.elephant.wms.input.core.component.noticedetail;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elephant.wms.common.infrastructure.template.compnent.Creater;
import com.elephant.wms.input.infrastructure.mapper.ReceiveNoticeDetailMapper;
import com.elephant.wms.input.infrastructure.po.ReceiveNoticeDetailPO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class SimpleReceiveNoticeDetailCreate extends Creater<ReceiveNoticeDetailPO> {

    @Resource
    private ReceiveNoticeDetailMapper receiveNoticeDetailMapper;

    @Override
    protected BaseMapper getMapper() {
        return receiveNoticeDetailMapper;
    }

}

package com.elephant.wms.basic.core.component.owner;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elephant.wms.basic.infrastructure.mapper.OwnerMapper;
import com.elephant.wms.basic.infrastructure.po.OwnerPO;
import com.elephant.wms.common.infrastructure.template.compnent.Creater;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class SimpleOwnerCreate extends Creater<OwnerPO> {

    @Resource
    private OwnerMapper ownerMapper;

    @Override
    protected BaseMapper getMapper() {
        return ownerMapper;
    }
}

package com.elephant.wms.core.component.owner;

import com.elephant.wms.infrastructure.mapper.OwnerMapper;
import com.elephant.wms.infrastructure.po.OwnerPO;
import com.elephant.wms.infrastructure.template.compnent.Creater;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class SimpleOwnerCreate extends Creater<OwnerPO> {

    @Resource
    private OwnerMapper ownerMapper;

    @Override
    @PostConstruct
    protected void init() {
        mapper = ownerMapper;
    }
}

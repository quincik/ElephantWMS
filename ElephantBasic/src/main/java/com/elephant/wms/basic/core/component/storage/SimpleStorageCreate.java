package com.elephant.wms.basic.core.component.storage;

import com.elephant.wms.basic.infrastructure.mapper.StorageMapper;
import com.elephant.wms.basic.infrastructure.po.StoragePO;
import com.elephant.wms.common.infrastructure.template.compnent.Creater;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class SimpleStorageCreate extends Creater<StoragePO> {

    @Resource
    private StorageMapper StorageMapper;

    @Override
    @PostConstruct
    protected void init() {
        mapper = StorageMapper;
    }
}

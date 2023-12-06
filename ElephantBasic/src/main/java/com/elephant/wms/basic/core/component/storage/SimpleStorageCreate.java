package com.elephant.wms.core.component.storage;

import com.elephant.wms.infrastructure.mapper.StorageMapper;
import com.elephant.wms.infrastructure.po.StoragePO;
import com.elephant.wms.infrastructure.template.compnent.Creater;
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

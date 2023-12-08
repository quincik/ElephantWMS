package com.elephant.wms.basic.core.component.storage;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elephant.wms.basic.infrastructure.mapper.StorageMapper;
import com.elephant.wms.basic.infrastructure.po.StoragePO;
import com.elephant.wms.common.infrastructure.template.compnent.MultiCreate;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class SimpleStorageCreate extends MultiCreate<StoragePO> {

    @Resource
    private StorageMapper storageMapper;

    @Override
    protected BaseMapper getMapper() {
        return storageMapper;
    }
}

package com.elephant.wms.basic.core.component.storage;

import com.elephant.wms.common.infrastructure.template.compnent.Parse;
import com.elephant.wms.basic.infrastructure.po.StoragePO;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class SimpleStorageParse extends Parse<StoragePO> {
    @Override
    @PostConstruct
    protected void init() {
        type = StoragePO.class;
    }
}

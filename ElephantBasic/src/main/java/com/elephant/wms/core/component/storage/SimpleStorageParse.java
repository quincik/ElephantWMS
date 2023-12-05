package com.elephant.wms.core.component.storage;

import com.elephant.wms.infrastructure.po.AreaPO;
import com.elephant.wms.infrastructure.po.StoragePO;
import com.elephant.wms.infrastructure.template.compnent.Parse;
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

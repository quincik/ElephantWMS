package com.elephant.wms.basic.core.component.storage;

import com.elephant.wms.basic.infrastructure.po.StoragePO;
import com.elephant.wms.common.infrastructure.template.compnent.Parse;
import org.springframework.stereotype.Component;

@Component
public class SimpleStorageParse extends Parse<StoragePO> {
    @Override
    protected Class<StoragePO> getType() {
        return StoragePO.class;
    }
}

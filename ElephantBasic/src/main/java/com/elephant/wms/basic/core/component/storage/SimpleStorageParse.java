package com.elephant.wms.basic.core.component.storage;

import com.elephant.wms.basic.infrastructure.po.StoragePO;
import com.elephant.wms.common.infrastructure.template.compnent.MultiParse;
import org.springframework.stereotype.Component;

@Component
public class SimpleStorageParse extends MultiParse<StoragePO> {
    @Override
    protected Class<StoragePO> getType() {
        return StoragePO.class;
    }
}

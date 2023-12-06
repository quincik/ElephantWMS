package com.elephant.wms.basic.core.component.owner;

import com.elephant.wms.basic.infrastructure.po.OwnerPO;
import com.elephant.wms.common.infrastructure.template.compnent.Parse;

import org.springframework.stereotype.Component;

@Component
public class SimpleOwnerParse extends Parse<OwnerPO> {
    @Override
    protected Class<OwnerPO> getType() {
        return OwnerPO.class;
    }
}

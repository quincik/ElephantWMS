package com.elephant.wms.basic.core.component.owner;

import com.elephant.wms.basic.infrastructure.po.OwnerPO;
import com.elephant.wms.common.infrastructure.template.compnent.MultiParse;

import org.springframework.stereotype.Component;

@Component
public class SimpleOwnerParse extends MultiParse<OwnerPO> {
    @Override
    protected Class<OwnerPO> getType() {
        return OwnerPO.class;
    }
}

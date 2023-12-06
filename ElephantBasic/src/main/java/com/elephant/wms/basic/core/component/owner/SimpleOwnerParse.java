package com.elephant.wms.basic.core.component.owner;

import com.elephant.wms.basic.infrastructure.po.OwnerPO;
import com.elephant.wms.common.infrastructure.template.compnent.Parse;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class SimpleOwnerParse extends Parse<OwnerPO> {
    @Override
    @PostConstruct
    protected void init() {
        type = OwnerPO.class;
    }
}

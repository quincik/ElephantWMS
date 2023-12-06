package com.elephant.wms.basic.core.component.area;

import com.elephant.wms.basic.infrastructure.po.AreaPO;
import com.elephant.wms.common.infrastructure.template.compnent.Parse;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class SimpleAreaParse extends Parse<AreaPO> {
    @Override
    @PostConstruct
    protected void init() {
        type = AreaPO.class;
    }
}

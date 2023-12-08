package com.elephant.wms.basic.core.component.area;

import com.elephant.wms.basic.infrastructure.po.AreaPO;
import com.elephant.wms.common.infrastructure.template.compnent.MultiParse;
import org.springframework.stereotype.Component;

@Component
public class SimpleAreaParse extends MultiParse<AreaPO> {
    @Override
    protected Class<AreaPO> getType() {
        return AreaPO.class;
    }
}

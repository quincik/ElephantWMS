package com.elephant.wms.basic.core.component.item;

import com.elephant.wms.basic.infrastructure.po.ItemPO;
import com.elephant.wms.common.infrastructure.template.compnent.Parse;
import org.springframework.stereotype.Component;

@Component
public class SimpleItemParse extends Parse<ItemPO> {
    @Override
    protected Class<ItemPO> getType() {
        return ItemPO.class;
    }
}

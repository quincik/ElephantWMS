package com.elephant.wms.core.component.item;

import com.elephant.wms.infrastructure.po.ItemPO;
import com.elephant.wms.infrastructure.template.compnent.Parse;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class SimpleItemParse extends Parse<ItemPO> {
    @Override
    @PostConstruct
    protected void init() {
        type = ItemPO.class;
    }
}

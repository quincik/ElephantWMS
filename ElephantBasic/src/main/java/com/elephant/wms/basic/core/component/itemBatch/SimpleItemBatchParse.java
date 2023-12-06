package com.elephant.wms.basic.core.component.itemBatch;

import com.elephant.wms.basic.infrastructure.po.ItemBatchPO;
import com.elephant.wms.common.infrastructure.template.compnent.Parse;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class SimpleItemBatchParse extends Parse<ItemBatchPO> {
    @Override
    @PostConstruct
    protected void init() {
        type = ItemBatchPO.class;
    }
}

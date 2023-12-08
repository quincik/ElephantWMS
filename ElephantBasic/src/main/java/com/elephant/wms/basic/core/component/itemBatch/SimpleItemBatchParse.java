package com.elephant.wms.basic.core.component.itemBatch;

import com.elephant.wms.basic.infrastructure.po.ItemBatchPO;
import com.elephant.wms.common.infrastructure.template.compnent.MultiParse;

import org.springframework.stereotype.Component;

@Component
public class SimpleItemBatchParse extends MultiParse<ItemBatchPO> {
    @Override
    protected Class<ItemBatchPO> getType() {
        return ItemBatchPO.class;
    }
}

package com.elephant.wms.basic.core.component.itemBatch;

import com.elephant.wms.basic.infrastructure.mapper.ItemBatchMapper;
import com.elephant.wms.basic.infrastructure.po.ItemBatchPO;
import com.elephant.wms.common.infrastructure.template.compnent.Creater;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class SimpleItemBatchCreate extends Creater<ItemBatchPO> {

    @Resource
    private ItemBatchMapper itemBatchMapper;

    @Override
    @PostConstruct
    protected void init() {
        mapper = itemBatchMapper;
    }
}

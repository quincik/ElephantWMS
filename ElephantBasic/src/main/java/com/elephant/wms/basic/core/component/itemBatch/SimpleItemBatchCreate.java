package com.elephant.wms.basic.core.component.itemBatch;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elephant.wms.basic.infrastructure.mapper.ItemBatchMapper;
import com.elephant.wms.basic.infrastructure.po.ItemBatchPO;
import com.elephant.wms.common.infrastructure.template.compnent.Creater;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class SimpleItemBatchCreate extends Creater<ItemBatchPO> {

    @Resource
    private ItemBatchMapper itemBatchMapper;

    @Override
    protected BaseMapper getMapper() {
        return itemBatchMapper;
    }
}

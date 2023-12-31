package com.elephant.wms.basic.core.component.item;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elephant.wms.basic.infrastructure.mapper.ItemMapper;
import com.elephant.wms.basic.infrastructure.po.ItemPO;
import com.elephant.wms.common.infrastructure.template.compnent.MultiCreate;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class SimpleItemCreate extends MultiCreate<ItemPO> {

    @Resource
    private ItemMapper itemMapper;
    @Override
    protected BaseMapper getMapper() {
        return itemMapper;
    }
}

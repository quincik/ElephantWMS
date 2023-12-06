package com.elephant.wms.core.component.item;

import com.elephant.wms.infrastructure.mapper.ItemMapper;
import com.elephant.wms.infrastructure.po.ItemPO;
import com.elephant.wms.infrastructure.template.compnent.Creater;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class SimpleItemCreate extends Creater<ItemPO> {

    @Resource
    private ItemMapper itemMapper;

    @Override
    @PostConstruct
    protected void init() {
        mapper = itemMapper;
    }
}

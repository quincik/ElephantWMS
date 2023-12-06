package com.elephant.wms.core.component.area;

import com.elephant.wms.infrastructure.mapper.AreaMapper;
import com.elephant.wms.infrastructure.po.AreaPO;
import com.elephant.wms.infrastructure.template.compnent.Creater;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class SimpleAreaCreate extends Creater<AreaPO> {

    @Resource
    private AreaMapper areaMapper;

    @Override
    @PostConstruct
    protected void init() {
        mapper = areaMapper;
    }
}

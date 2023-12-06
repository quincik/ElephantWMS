package com.elephant.wms.basic.core.component.area;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elephant.wms.basic.infrastructure.mapper.AreaMapper;
import com.elephant.wms.basic.infrastructure.po.AreaPO;
import com.elephant.wms.common.infrastructure.template.compnent.Creater;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class SimpleAreaCreate extends Creater<AreaPO> {

    @Resource
    private AreaMapper areaMapper;

    @Override
    protected BaseMapper getMapper() {
        return areaMapper;
    }

}

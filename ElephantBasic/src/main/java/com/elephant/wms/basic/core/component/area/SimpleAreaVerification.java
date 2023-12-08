package com.elephant.wms.basic.core.component.area;

import com.elephant.wms.basic.infrastructure.po.AreaPO;
import com.elephant.wms.common.infrastructure.template.compnent.MultiVerification;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class SimpleAreaVerification extends MultiVerification<AreaPO> {
    @Override
    public @Nonnull List<String> verified(AreaPO entity) {
        List<String> result = new LinkedList<>();
        if(null == entity.getCode() || entity.getCode().isBlank()){
            result.add("编码为空");
        }
        if(null == entity.getType()){
            result.add("类型为空");
        }
        return result;
    }

}

package com.elephant.wms.core.component.area.customization;

import com.elephant.wms.core.component.area.SimpleAreaVerification;
import com.elephant.wms.infrastructure.object.Result;
import com.elephant.wms.infrastructure.po.AreaPO;
import jakarta.annotation.Nonnull;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VerificationDimensionalArea extends SimpleAreaVerification {

    @Override
    public @Nonnull List<String> verified(AreaPO entity) {

        List<String> result = super.verified(entity);
        if(null == entity.getWidth()){
            result.add("宽度为空");
        }
        if(null == entity.getHeight()){
            result.add("高度为空");
        }
        if(null == entity.getLength()){
            result.add("长度为空");
        }
        return result;
    }
}

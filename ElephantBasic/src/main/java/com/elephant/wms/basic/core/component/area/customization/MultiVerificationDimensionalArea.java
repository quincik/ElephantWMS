package com.elephant.wms.basic.core.component.area.customization;

import com.elephant.wms.basic.core.component.area.SimpleAreaVerification;
import com.elephant.wms.basic.infrastructure.po.AreaPO;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MultiVerificationDimensionalArea extends SimpleAreaVerification {

    @Override
    public @Nonnull List<String> verifiedEntityExt(AreaPO entity) {

        List<String> result = super.verifiedEntityExt(entity);
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

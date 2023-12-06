package com.elephant.wms.core.component.owner;

import com.elephant.wms.infrastructure.po.OwnerPO;
import com.elephant.wms.infrastructure.template.compnent.Verification;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class SimpleOwnerVerification extends Verification<OwnerPO> {
    @Override
    public @Nonnull List<String> verified(OwnerPO entity) {
        List<String> result = new LinkedList<>();
        if(null == entity.getCode() || entity.getCode().isBlank()){
            result.add("编码为空");
        }
        return result;
    }

}

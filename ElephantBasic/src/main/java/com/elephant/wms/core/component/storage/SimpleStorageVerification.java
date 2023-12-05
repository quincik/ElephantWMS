package com.elephant.wms.core.component.storage;

import com.elephant.wms.infrastructure.po.StoragePO;
import com.elephant.wms.infrastructure.template.compnent.Verification;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class SimpleStorageVerification extends Verification<StoragePO> {
    @Override
    public @Nonnull List<String> verified(StoragePO entity) {
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

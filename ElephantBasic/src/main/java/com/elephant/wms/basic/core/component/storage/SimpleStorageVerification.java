package com.elephant.wms.basic.core.component.storage;

import com.elephant.wms.common.infrastructure.template.compnent.Verification;
import com.elephant.wms.basic.infrastructure.po.StoragePO;
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
        if(null == entity.getAreaCode()){
            result.add("库区为空");
        }
        return result;
    }

}

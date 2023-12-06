package com.elephant.wms.core.component.item;

import com.elephant.wms.infrastructure.po.ItemPO;
import com.elephant.wms.infrastructure.template.compnent.Verification;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class SimpleItemVerification extends Verification<ItemPO> {
    @Override
    public @Nonnull List<String> verified(ItemPO entity) {
        List<String> result = new LinkedList<>();
        if(null == entity.getCode() || entity.getCode().isBlank()){
            result.add("编码为空");
        }
        return result;
    }

}

package com.elephant.wms.basic.core.component.item;

import com.elephant.wms.common.infrastructure.template.compnent.MultiVerification;
import com.elephant.wms.basic.infrastructure.po.ItemPO;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class SimpleItemVerification extends MultiVerification<ItemPO> {
    @Override
    public @Nonnull List<String> verified(ItemPO entity) {
        List<String> result = new LinkedList<>();
        if(null == entity.getCode() || entity.getCode().isBlank()){
            result.add("编码为空");
        }
        return result;
    }

}

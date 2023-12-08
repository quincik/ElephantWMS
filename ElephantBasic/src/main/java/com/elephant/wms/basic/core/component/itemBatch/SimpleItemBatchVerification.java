package com.elephant.wms.basic.core.component.itemBatch;

import com.elephant.wms.basic.infrastructure.po.ItemBatchPO;
import com.elephant.wms.common.infrastructure.template.compnent.MultiVerification;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class SimpleItemBatchVerification extends MultiVerification<ItemBatchPO> {
    @Override
    public @Nonnull List<String> verified(ItemBatchPO entity) {
        List<String> result = new LinkedList<>();
        if(null == entity.getOwnerCode() || entity.getOwnerCode().isBlank()){
            result.add("货主编码为空");
        }
        return result;
    }

}

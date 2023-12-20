package com.elephant.wms.basic.core.component.itemBatch;

import com.elephant.wms.basic.infrastructure.po.ItemBatchPO;
import com.elephant.wms.common.infrastructure.template.compnent.MultiVerification;
import com.elephant.wms.common.infrastructure.template.compnent.SingerVerification;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class SimpleItemBatchVerification extends SingerVerification<ItemBatchPO> {

    @Override
    protected Class<ItemBatchPO> getType() {
        return ItemBatchPO.class;
    }

    @Override
    public @Nonnull List<String> verifiedEntityExt(ItemBatchPO entity) {
        List<String> result = new LinkedList<>();
        if(null == entity.getOwnerCode() || entity.getOwnerCode().isBlank()){
            result.add("货主编码为空");
        }
        return result;
    }

}

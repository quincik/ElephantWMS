package com.elephant.wms.basic.core.component.itemBatch;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elephant.wms.basic.infrastructure.mapper.ItemBatchMapper;
import com.elephant.wms.basic.infrastructure.po.ItemBatchPO;
import com.elephant.wms.common.infrastructure.object.Result;
import com.elephant.wms.common.infrastructure.template.compnent.MultiCreate;

import jakarta.annotation.Resource;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class SimpleItemBatchCreate implements Processor {

    @Resource
    private ItemBatchMapper itemBatchMapper;

    @Override
    public void process(Exchange exchange) throws Exception {
        ItemBatchPO entity =
                (ItemBatchPO) exchange.getMessage().getBody(Result.class).getData();
        int count = itemBatchMapper.insert(entity);
        if(0 == count){
            exchange.getMessage().setBody(new Result<>(false, "批次落库失败"));
            return;
        }
        exchange.getMessage().setBody(new Result<>(entity.getId()));
    }
}

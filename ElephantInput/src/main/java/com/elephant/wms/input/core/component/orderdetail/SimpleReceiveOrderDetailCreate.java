package com.elephant.wms.input.core.component.orderdetail;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.elephant.wms.basic.interfaces.service.ItemBatchService;
import com.elephant.wms.common.infrastructure.object.Result;
import com.elephant.wms.input.core.enums.ReceiveNoticeStatus;
import com.elephant.wms.input.core.enums.ReceiveOrderStatus;
import com.elephant.wms.input.infrastructure.mapper.ReceiveNoticeDetailMapper;
import com.elephant.wms.input.infrastructure.mapper.ReceiveNoticeMapper;
import com.elephant.wms.input.infrastructure.mapper.ReceiveOrderDetailMapper;
import com.elephant.wms.input.infrastructure.mapper.ReceiveOrderMapper;
import com.elephant.wms.input.infrastructure.po.ReceiveNoticeDetailPO;
import com.elephant.wms.input.infrastructure.po.ReceiveNoticePO;
import com.elephant.wms.input.infrastructure.po.ReceiveOrderDetailPO;
import com.elephant.wms.input.infrastructure.po.ReceiveOrderPO;
import jakarta.annotation.Resource;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class SimpleReceiveOrderDetailCreate implements Processor {

    @Resource
    private ReceiveOrderDetailMapper receiveOrderDetailMapper;
    @Resource
    private ReceiveOrderMapper receiveOrderMapper;
    @Resource
    private ItemBatchService itemBatchService;

    @Override
    @Transactional
    public void process(Exchange exchange) throws Exception {

        ReceiveOrderDetailPO entity = exchange.getMessage().getBody(ReceiveOrderDetailPO.class);
        ReceiveOrderPO receiveOrder = exchange.getMessage()
                .getHeader("receiveOrder", ReceiveOrderPO.class);
        ReceiveOrderPO updateOrder= exchange.getMessage()
                .getHeader("updateOrder", ReceiveOrderPO.class);
        Map<String,Object> itemBatchParam = exchange.getMessage()
                .getHeader("itemBatchParam", HashMap.class);

        Optional<Long> itemBatch =  itemBatchService.create(itemBatchParam);

        entity.setItemBatchId(itemBatch.get());
        int count = receiveOrderDetailMapper.insert(entity);
        if(0 == count){
            exchange.getMessage().setBody(new Result<>(false, "收货通知明细新增失败"));
            return;
        }

        UpdateWrapper<ReceiveOrderPO> condition = new UpdateWrapper<>();
        condition.eq("code",receiveOrder.getCode());
        condition.ne("status", ReceiveOrderStatus.COMPLETE.getCode());
        // 乐观锁更新数量，防并发（验证组件中已经验证 收货明细数量 > 0，不存在 ABA）
        condition.eq("actual_quantity",receiveOrder.getActualQuantity());

        count = receiveOrderMapper.update(updateOrder,condition);
        if(0 == count){
            exchange.getMessage().setBody(new Result<>(false, "收货单更新失败，请稍后重试"));
            // 抛异常回滚明细
            throw new RuntimeException("收货单更新失败，请稍后重试");
        }
        exchange.getMessage().setBody(new Result<>(entity.getId()));
    }

}

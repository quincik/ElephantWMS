package com.elephant.wms.input.core.component.order;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.elephant.wms.common.infrastructure.object.Result;
import com.elephant.wms.input.core.enums.ReceiveNoticeStatus;
import com.elephant.wms.input.infrastructure.mapper.ReceiveNoticeDetailMapper;
import com.elephant.wms.input.infrastructure.mapper.ReceiveNoticeMapper;
import com.elephant.wms.input.infrastructure.mapper.ReceiveOrderMapper;
import com.elephant.wms.input.infrastructure.po.ReceiveNoticeDetailPO;
import com.elephant.wms.input.infrastructure.po.ReceiveNoticePO;
import com.elephant.wms.input.infrastructure.po.ReceiveOrderPO;
import jakarta.annotation.Resource;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SimpleReceiveOrderCreate implements Processor {

    @Resource
    ReceiveOrderMapper receiveOrderMapper;


    @Override
    @Transactional
    public void process(Exchange exchange) throws Exception {

        ReceiveOrderPO entity = exchange.getMessage().getBody(ReceiveOrderPO.class);

        int count = receiveOrderMapper.insertCondition(entity);
        if(0 == count){
            exchange.getMessage().setBody(new Result<>(false, "收货订单新增失败"));
            return;
        }
        exchange.getMessage().setBody(new Result<>(entity.getCode()));
    }

}

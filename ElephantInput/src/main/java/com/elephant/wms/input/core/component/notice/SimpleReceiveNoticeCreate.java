package com.elephant.wms.input.core.component.notice;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elephant.wms.common.infrastructure.object.Result;
import com.elephant.wms.common.infrastructure.template.compnent.Creater;
import com.elephant.wms.input.infrastructure.mapper.ReceiveNoticeMapper;
import com.elephant.wms.input.infrastructure.po.ReceiveNoticePO;
import jakarta.annotation.Resource;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SimpleReceiveNoticeCreate implements Processor {

    @Resource
    private ReceiveNoticeMapper receiveNoticeMapper;

    @Override
    @Transactional
    public void process(Exchange exchange) throws Exception {

        ReceiveNoticePO entity = exchange.getMessage().getBody(ReceiveNoticePO.class);
        int count = receiveNoticeMapper.insert(entity);
        if(0 == count){
            exchange.getMessage().setBody(new Result<>(false, "收货通知落库失败"));
            return;
        }
        exchange.getMessage().setBody(new Result<>(entity.getCode()));
    }
}

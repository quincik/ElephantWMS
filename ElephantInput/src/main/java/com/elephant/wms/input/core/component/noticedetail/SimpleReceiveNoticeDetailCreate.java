package com.elephant.wms.input.core.component.noticedetail;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elephant.wms.common.infrastructure.object.Result;
import com.elephant.wms.common.infrastructure.template.compnent.Creater;
import com.elephant.wms.input.core.enums.ReceiveNoticeStatus;
import com.elephant.wms.input.infrastructure.mapper.ReceiveNoticeDetailMapper;
import com.elephant.wms.input.infrastructure.mapper.ReceiveNoticeMapper;
import com.elephant.wms.input.infrastructure.po.ReceiveNoticeDetailPO;
import com.elephant.wms.input.infrastructure.po.ReceiveNoticePO;
import jakarta.annotation.Resource;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class SimpleReceiveNoticeDetailCreate implements Processor {

    @Resource
    private ReceiveNoticeDetailMapper receiveNoticeDetailMapper;
    @Resource
    private ReceiveNoticeMapper receiveNoticeMapper;

    @Override
    @Transactional
    public void process(Exchange exchange) throws Exception {

        ReceiveNoticeDetailPO entity = (ReceiveNoticeDetailPO) exchange.getMessage()
                .getBody(Result.class).getData();
        ReceiveNoticePO receiveNotice = exchange.getMessage()
                .getHeader("receiveNotice", ReceiveNoticePO.class);

        int count = receiveNoticeDetailMapper.insert(entity);
        if(0 == count){
            exchange.getMessage().setBody(new Result<>(false, "收货通知明细新增失败"));
            return;
        }

        ReceiveNoticePO updateNotice = new ReceiveNoticePO();
        updateNotice.setQuantity(receiveNotice.getQuantity() + entity.getQuantity());

        UpdateWrapper<ReceiveNoticePO> condition = new UpdateWrapper<>();
        condition.eq("code",receiveNotice.getCode());
        condition.eq("status", ReceiveNoticeStatus.CREATE.getCode());
        // 乐观锁更新数量，防并发（验证组件中已经验证 收货明细数量 > 0，不存在 ABA）
        condition.eq("quantity",receiveNotice.getQuantity());

        count = receiveNoticeMapper.update(updateNotice,condition);
        if(0 == count){
            exchange.getMessage().setBody(new Result<>(false, "收货通知单更新失败，请稍后重试"));
            // 抛异常回滚明细
            throw new RuntimeException("收货通知单更新失败，请稍后重试");
        }
        exchange.getMessage().setBody(new Result<>(entity.getId()));
    }

}

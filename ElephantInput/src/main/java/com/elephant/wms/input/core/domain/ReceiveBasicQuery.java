package com.elephant.wms.input.core.domain;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elephant.wms.input.infrastructure.mapper.ReceiveNoticeMapper;
import com.elephant.wms.input.infrastructure.mapper.ReceiveOrderMapper;
import com.elephant.wms.input.infrastructure.po.ReceiveNoticePO;
import com.elephant.wms.input.infrastructure.po.ReceiveOrderDetailPO;
import com.elephant.wms.input.infrastructure.po.ReceiveOrderPO;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class ReceiveBasicQuery {

    @Resource
    private  ReceiveOrderMapper receiveOrderMapper;

    @Resource
    private ReceiveNoticeMapper receiveNoticeMapper;

    public  ReceiveOrderPO getOrder(String code){
        QueryWrapper<ReceiveOrderPO> query = new QueryWrapper<>();
        query.eq("code",code);
        return receiveOrderMapper.selectOne(query,false);
    }

    public ReceiveNoticePO getNotice(String code){
        QueryWrapper<ReceiveNoticePO> query = new QueryWrapper<>();
        query.eq("code",code);
        return receiveNoticeMapper.selectOne(query,false);
    }
}

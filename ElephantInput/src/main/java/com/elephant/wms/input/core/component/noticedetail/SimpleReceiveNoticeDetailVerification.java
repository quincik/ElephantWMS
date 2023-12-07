package com.elephant.wms.input.core.component.noticedetail;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elephant.wms.basic.infrastructure.mapper.ItemMapper;
import com.elephant.wms.basic.interfaces.service.ItemService;
import com.elephant.wms.basic.interfaces.service.OwnerService;
import com.elephant.wms.common.infrastructure.object.Result;
import com.elephant.wms.common.infrastructure.template.compnent.Verification;
import com.elephant.wms.input.core.enums.ReceiveNoticeStatus;
import com.elephant.wms.input.infrastructure.mapper.ReceiveNoticeDetailMapper;
import com.elephant.wms.input.infrastructure.mapper.ReceiveNoticeMapper;
import com.elephant.wms.input.infrastructure.po.ReceiveNoticeDetailPO;
import com.elephant.wms.input.infrastructure.po.ReceiveNoticePO;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

@Component
public class SimpleReceiveNoticeDetailVerification implements Processor {

    @Resource
    ReceiveNoticeMapper receiveNoticeMapper;

    @Resource
    ReceiveNoticeDetailMapper receiveNoticeDetailMapper;

    @Resource
    ItemService itemService;

    protected @Nonnull List<String> verified(ReceiveNoticeDetailPO entity,Exchange exchange) {

        List<String> result = new LinkedList<>();

        if(null == entity.getQuantity() || entity.getQuantity() < 1){
            result.add("[命中收货通知明细规则]收货明细数量不能少于 1。");
        }

        QueryWrapper<ReceiveNoticePO> query = new QueryWrapper<>();
        query.eq("code",entity.getReceiveNoticeCode());
        ReceiveNoticePO receiveNoticePO = receiveNoticeMapper.selectOne(query);
        if(null == receiveNoticePO){
            result.add("[命中收货通知明细规则]未找到对应通知单。");
        }
        if(result.isEmpty()){
            exchange.getMessage().setHeader("receiveNotice",receiveNoticePO);
            if(!receiveNoticePO.getStatus().equals(ReceiveNoticeStatus.CREATE.getCode())){
                result.add("[命中收货通知明细规则]通知单非创建状态，无法新增明细。");
            }
            if( !receiveNoticePO.getOwnerCode().equals(entity.getOwnerCode()) ){
                result.add("[命中收货通知明细规则]明细与通知单货主不一致。");
            }
            Optional<ItemService.ItemDTO> itemDTO = itemService.queryByCode(entity.getItemCode(),entity.getOwnerCode());
            if(itemDTO.isEmpty()){
                result.add("[命中收货通知明细规则]货主下无此商品，请核实维护 或 更改仓库管理流程");
            }
            QueryWrapper<ReceiveNoticeDetailPO> queryDetail = new QueryWrapper<>();
            queryDetail.eq("receive_notice_code",receiveNoticePO.getCode());
            queryDetail.eq("item_code",entity.getItemCode());
            List<ReceiveNoticeDetailPO> details = receiveNoticeDetailMapper.selectList(queryDetail);
            if(!details.isEmpty()){
                result.add("[命中收货通知明细规则]同一通知单同一商品仅支持一条明细，该商品已存在。");
            }
        }
        return result;
    }

    @Override
    public void  process(Exchange exchange) throws Exception {

        ReceiveNoticeDetailPO entity = exchange.getMessage().getBody(ReceiveNoticeDetailPO.class);

        if(null == entity){
            exchange.getMessage().setBody(new Result<>(false, "[命中收货通知明细规则]收货通知明细信息为空"));
            return;
        }

        List<String> errors = verified(entity,exchange);

        if(errors.isEmpty()) {
            exchange.getMessage().setBody( new Result<>(entity));
            return;
        }

        exchange.getMessage().setBody( new Result<>(false,errors));
    }


}

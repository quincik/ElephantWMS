package com.elephant.wms.input.core.component.noticedetail;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elephant.wms.basic.interfaces.service.ItemService;
import com.elephant.wms.common.infrastructure.object.Result;
import com.elephant.wms.common.infrastructure.template.compnent.SingerVerification;
import com.elephant.wms.input.core.domain.ReceiveBasicQuery;
import com.elephant.wms.input.core.enums.ReceiveNoticeStatus;
import com.elephant.wms.input.core.enums.ReceiveOrderStatus;
import com.elephant.wms.input.infrastructure.mapper.ReceiveNoticeDetailMapper;
import com.elephant.wms.input.infrastructure.mapper.ReceiveNoticeMapper;
import com.elephant.wms.input.infrastructure.po.ReceiveNoticeDetailPO;
import com.elephant.wms.input.infrastructure.po.ReceiveNoticePO;
import com.elephant.wms.input.infrastructure.po.ReceiveOrderDetailPO;
import com.elephant.wms.input.infrastructure.po.ReceiveOrderPO;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static com.elephant.wms.common.infrastructure.enums.INPUT_ERROR.*;

@Component
public class SimpleReceiveNoticeDetailVerification extends SingerVerification<ReceiveNoticeDetailPO> {



    @Resource
    ReceiveNoticeDetailMapper receiveNoticeDetailMapper;

    @Resource
    ItemService itemService;

    @Resource
    ReceiveBasicQuery receiveBasicQuery;

    @Override
    protected Class<ReceiveNoticeDetailPO> getType() {
        return ReceiveNoticeDetailPO.class;
    }

    @Nonnull
    @Override
    protected List<String> verifiedEntityExt(@Nonnull ReceiveNoticeDetailPO entity) {

        List<String> result = new LinkedList<>();

        if(null == entity.getQuantity() || entity.getQuantity() < 1){
            result.add(IN02B210001.getDesc());
        }
        Optional<ItemService.ItemDTO> itemDTO =
                itemService.queryByCode(entity.getItemCode(),entity.getOwnerCode());
        appendError(result, emptyError(itemDTO,IN00B0000001.getDesc()));
        return result;
    }

    protected @Nonnull List<String> verifiedNoticeExt(ReceiveNoticePO notice){

        List<String> result = new LinkedList<>();
        if(null == notice){
            result.add(IN02B210002.getDesc());
        }
        if(!notice.getStatus().equals(ReceiveNoticeStatus.CREATE.getCode())){
            result.add(IN02B210003.getDesc());
        }
        return  result;
    }

    protected @Nonnull List<String> verifiedNoticeDetailRefExt(ReceiveNoticePO notice,
                                                               ReceiveNoticeDetailPO detail){
        List<String> result = new LinkedList<>();
        if( null != notice.getOwnerCode() &&
                !notice.getOwnerCode().equals(detail.getOwnerCode()) ){
            result.add(IN02B210004.getDesc());
        }

        QueryWrapper<ReceiveNoticeDetailPO> queryDetail = new QueryWrapper<>();
        queryDetail.eq("receive_notice_code",notice.getCode());
        queryDetail.eq("item_code",detail.getItemCode());

        List<ReceiveNoticeDetailPO> details = receiveNoticeDetailMapper.selectList(queryDetail);
        appendError(result,notEmptyError(details,IN02B210005.getDesc()));

        return result;
    }

    @Nonnull
    @Override
    protected List<String> verifiedExt(@Nonnull ReceiveNoticeDetailPO entity, Exchange exchange) {

        List<String> result = new LinkedList<>();

        ReceiveNoticePO receiveNoticePO = receiveBasicQuery
                .getNotice(entity.getReceiveNoticeCode());
        appendError(result,verifiedNoticeExt(receiveNoticePO));

        if(null != receiveNoticePO){
            exchange.getMessage().setHeader("receiveNotice",receiveNoticePO);
            appendError(result,verifiedNoticeDetailRefExt(receiveNoticePO,entity));
        }



        return result;
    }
}

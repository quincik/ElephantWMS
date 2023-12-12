package com.elephant.wms.input.core.component.orderdetail.noticeReceive;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elephant.wms.input.core.component.orderdetail.SimpleReceiveOrderDetailVerification;
import com.elephant.wms.input.infrastructure.mapper.ReceiveNoticeDetailMapper;
import com.elephant.wms.input.infrastructure.po.ReceiveNoticeDetailPO;
import com.elephant.wms.input.infrastructure.po.ReceiveOrderDetailPO;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import static com.elephant.wms.input.core.enums.ReceiveNoticeDetailStatus.COMPLETE;

@Component
public class NoticeReceiveOrderDetailVerification  extends SimpleReceiveOrderDetailVerification {

    @Resource
    ReceiveNoticeDetailMapper receiveNoticeDetailMapper;

    @Nonnull
    protected List<String> verifiedNoticeDetail(ReceiveOrderDetailPO entity, Exchange exchange){

        List<String> result = new LinkedList<>();

        QueryWrapper<ReceiveNoticeDetailPO> query = new QueryWrapper<>();
        query.eq("item_code",entity.getItemCode());
        if(null != entity.getOwnerCode() && !entity.getOwnerCode().isEmpty()){
            query.eq("owner_code",entity.getItemCode());
        }
        query.ne("status",COMPLETE.getCode());
        query.orderByDesc("receive_notice_code");

        List<ReceiveNoticeDetailPO> details = receiveNoticeDetailMapper.selectList(query);
        if(null == details || details.isEmpty()){
            appendError(result,"未匹配到通知单明细");
            return result;
        }
        exchange.getMessage().setHeader("noticeDetails", details);

        Set<String> owners = new HashSet<>();
        AtomicInteger count = new AtomicInteger();
        details.stream().forEach(item->{
            owners.add(item.getOwnerCode());
            count.addAndGet(item.getQuantity() - item.getActualQuantity());
        });

        //TODO
        exchange.getMessage().setHeader("owners",owners);
        appendError(result, conditionError(1!=owners.size()
                ,"该商品存在多货主收货明细"));
        appendError(result, conditionError(count.get() < entity.getActualQuantity()
                ,"商品数量多于通知单明细数量"));

        return result;
    }

    @Override
    protected List<String> verifiedExt(ReceiveOrderDetailPO entity, Exchange exchange) {

        List<String> result = super.verifiedExt(entity,exchange);
        appendError(result,verifiedNoticeDetail(entity,exchange));
        return result;

    }
}

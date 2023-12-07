package com.elephant.wms.input.core.component.order;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elephant.wms.basic.infrastructure.po.StoragePO;
import com.elephant.wms.basic.interfaces.service.StorageService;
import com.elephant.wms.common.infrastructure.object.Result;
import com.elephant.wms.input.infrastructure.mapper.ReceiveOrderMapper;
import com.elephant.wms.input.infrastructure.po.ReceiveOrderPO;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static com.elephant.wms.input.core.enums.ReceiveOrderStatus.COMPLETE;

@Component
public class SimpleReceiveOrderVerification implements Processor {

    protected @Nonnull String verifiedStorageExt(StorageService.StorageDTO storageDTO){
        return "";
    }

    @Resource
    StorageService storageService;
    @Resource
    ReceiveOrderMapper receiveOrderMapper;

    protected @Nonnull List<String> verified(ReceiveOrderPO entity,Exchange exchange) {

        List<String> result = new LinkedList<>();
        if(null == entity.getStorageCode() || entity.getStorageCode().isEmpty()){
            result.add("收货单库位为空");
        }
        if(result.isEmpty()){
           Optional<StorageService.StorageDTO> storageDTO = storageService.queryByCode(entity.getStorageCode());
           if(storageDTO.isEmpty()){
              result.add("为查找到库位");
           }
           if(result.isEmpty()){
               String errorInfo = verifiedStorageExt(storageDTO.get());
               if(!errorInfo.isEmpty()) result.add(errorInfo);
           }
        }
        if(null == entity.getOperatorId()){
            result.add("操作人不能为空");
        }

        QueryWrapper<ReceiveOrderPO> query = new QueryWrapper<>();
        query.eq("storage_code",entity.getStorageCode());
        query.ne("status",COMPLETE.getCode());
        List<ReceiveOrderPO> orders = receiveOrderMapper.selectList(query);
        if(!orders.isEmpty()){
            result.add("该容器已存在未完成收货单");
        }

        return result;
    }

    @Override
    public void  process(Exchange exchange) throws Exception {

        ReceiveOrderPO entity = exchange.getMessage().getBody(ReceiveOrderPO.class);

        if(null == entity){
            exchange.getMessage().setBody(new Result<>(false, "[命中收货订单明细规则]收货订单信息为空"));
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

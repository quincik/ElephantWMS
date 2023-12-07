package com.elephant.wms.input.core.component.notice;

import com.elephant.wms.basic.interfaces.service.OwnerService;
import com.elephant.wms.common.infrastructure.object.Result;
import com.elephant.wms.input.infrastructure.po.ReceiveNoticePO;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class SimpleReceiveNoticeVerification implements Processor {

    @Resource
    OwnerService ownerService;

    protected @Nonnull List<String> verified(ReceiveNoticePO entity,Exchange exchange) {
        List<String> result = new LinkedList<>();
        if(null == entity.getOwnerCode() || entity.getOwnerCode().isEmpty()){
            result.add("[命中收货通知规则]货主编码为空。");
        }

        if(result.isEmpty() ){
            Optional<OwnerService.OwnerDTO> ownerDTO = ownerService.queryByCode(entity.getOwnerCode());
            if(!ownerDTO.isPresent()) {
                result.add("[命中收货通知规则]货主编码有误。");
            }else {
                exchange.getMessage().setHeader("owner",ownerDTO);
            }
        }

        return result;
    }

    public void  process(Exchange exchange) throws Exception {

        ReceiveNoticePO entity = exchange.getMessage().getBody(ReceiveNoticePO.class);

        if(null == entity){
            exchange.getMessage().setBody(new Result<>(false, "[命中收货通知规则]收货通知信息为空"));
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

package com.elephant.wms.input.core.component.notice;

import com.elephant.wms.basic.interfaces.service.OwnerService;
import com.elephant.wms.common.infrastructure.object.Result;
import com.elephant.wms.common.infrastructure.template.compnent.SingerVerification;
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
public class SimpleReceiveNoticeVerification extends SingerVerification<ReceiveNoticePO> {

    @Resource
    OwnerService ownerService;

    @Override
    protected Class<ReceiveNoticePO> getType() {
        return ReceiveNoticePO.class;
    }

    @Override
    protected @Nonnull List<String> verifiedEntityExt(ReceiveNoticePO entity) {
        List<String> result = new LinkedList<>();
        if(null == entity.getOwnerCode() || entity.getOwnerCode().isEmpty()){
            result.add(IN00B0000002.getDesc());
        }
        return result;
    }

    @Nonnull
    @Override
    protected List<String> verifiedExt(ReceiveNoticePO entity, Exchange exchange) {

        List<String> result = new LinkedList<>();
        Optional<OwnerService.OwnerDTO> ownerDTO = ownerService.queryByCode(entity.getOwnerCode());
        appendError(result, emptyError(ownerDTO,IN00B0000003.getDesc()));
        exchange.getMessage().setHeader("owner",ownerDTO);

        return result;
    }

}

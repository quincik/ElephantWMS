package com.elephant.wms.input.core.component.order;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elephant.wms.basic.interfaces.service.StorageService;
import com.elephant.wms.common.infrastructure.template.compnent.SingerVerification;
import com.elephant.wms.input.infrastructure.mapper.ReceiveOrderMapper;
import com.elephant.wms.input.infrastructure.po.ReceiveOrderPO;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import org.apache.camel.Exchange;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static com.elephant.wms.common.infrastructure.enums.INPUT_ERROR.*;
import static com.elephant.wms.input.core.enums.ReceiveOrderStatus.COMPLETE;

@Component
public class SimpleReceiveOrderVerification extends SingerVerification<ReceiveOrderPO> {



    @Resource
    StorageService storageService;
    @Resource
    ReceiveOrderMapper receiveOrderMapper;

    @Override
    protected Class<ReceiveOrderPO> getType() {
        return ReceiveOrderPO.class;
    }

    @Nonnull
    @Override
    protected List<String> verifiedEntityExt(@Nonnull ReceiveOrderPO entity) {
        List<String> result = new LinkedList<>();

        if (null == entity.getStorageCode() || entity.getStorageCode().isEmpty()) {
            result.add(IN01B100001.getDesc());
        }
        if (null == entity.getOperatorId()) {
            result.add(IN01B100002.getDesc());
        }

        return result;
    }

    protected @Nonnull List<String> verifiedStorageExt( @Nonnull StorageService.StorageDTO storageDTO) {

        QueryWrapper<ReceiveOrderPO> query = new QueryWrapper<>();
        query.eq("storage_code", storageDTO.getCode());
        query.ne("status", COMPLETE.getCode());
        List<ReceiveOrderPO> orders = receiveOrderMapper.selectList(query);
        if (!orders.isEmpty()) {
           return List.of(IN01B101001.getDesc());
        }
        return new LinkedList<>();
    }

    @Nonnull
    @Override
    protected List<String> verifiedExt(@Nonnull ReceiveOrderPO entity, Exchange exchange) {

        List<String> result = new LinkedList<>();

        Optional<StorageService.StorageDTO> storageDTO = storageService.queryByCode(entity.getStorageCode());
        appendError(result,emptyError(storageDTO, IN00B0000004.getDesc()));

        if (!storageDTO.isEmpty()) {
            appendError(result,verifiedStorageExt(storageDTO.get()));
        }

        return result;
    }
}

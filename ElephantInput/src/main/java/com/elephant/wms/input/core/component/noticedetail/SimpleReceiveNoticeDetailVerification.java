package com.elephant.wms.input.core.component.noticedetail;

import com.elephant.wms.common.infrastructure.template.compnent.Verification;
import com.elephant.wms.input.infrastructure.po.ReceiveNoticeDetailPO;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class SimpleReceiveNoticeDetailVerification extends Verification<ReceiveNoticeDetailPO> {
    @Override
    public @Nonnull List<String> verified(ReceiveNoticeDetailPO entity) {
        List<String> result = new LinkedList<>();

        return result;
    }

}

package com.elephant.wms.input.core.component.notice;

import com.elephant.wms.common.infrastructure.template.compnent.Verification;
import com.elephant.wms.input.infrastructure.po.ReceiveNoticePO;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class SimpleReceiveNoticeVerification extends Verification<ReceiveNoticePO> {
    @Override
    public @Nonnull List<String> verified(ReceiveNoticePO entity) {
        List<String> result = new LinkedList<>();

        return result;
    }

}

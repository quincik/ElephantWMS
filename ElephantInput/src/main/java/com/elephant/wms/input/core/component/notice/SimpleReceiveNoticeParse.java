package com.elephant.wms.input.core.component.notice;

import com.elephant.wms.common.infrastructure.template.compnent.Parse;
import com.elephant.wms.input.infrastructure.po.ReceiveNoticePO;
import org.springframework.stereotype.Component;

@Component
public class SimpleReceiveNoticeParse extends Parse<ReceiveNoticePO> {
    @Override
    protected Class<ReceiveNoticePO> getType() {
        return ReceiveNoticePO.class;
    }
}

package com.elephant.wms.input.core.component.noticedetail;

import com.elephant.wms.common.infrastructure.template.compnent.Parse;
import com.elephant.wms.input.infrastructure.po.ReceiveNoticeDetailPO;
import org.springframework.stereotype.Component;

@Component
public class SimpleReceiveNoticeDetailParse extends Parse<ReceiveNoticeDetailPO> {
    @Override
    protected Class<ReceiveNoticeDetailPO> getType() {
        return ReceiveNoticeDetailPO.class;
    }
}

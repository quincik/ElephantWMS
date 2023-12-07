package com.elephant.wms.input.infrastructure.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("receive_notice_detail_relation")
public class ReceiveNoticeDetailRelationPO {
    private Long id;
    private Long receiveNoticeDetailId;
    private Long receiveOrderDetailId;
}

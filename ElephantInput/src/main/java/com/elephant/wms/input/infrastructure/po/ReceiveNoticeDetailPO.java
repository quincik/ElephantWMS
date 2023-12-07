package com.elephant.wms.input.infrastructure.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("receive_notice_detail")
public class ReceiveNoticeDetailPO {
    private Long id;
    private String receiveNoticeCode;
    private String ownerCode;
    private String itemCode;
    private Integer quantity;
    private Integer actualQuantity;
    private String extend;
    private Integer status;
    private String createdTime;
    private String modifiedTime;
}

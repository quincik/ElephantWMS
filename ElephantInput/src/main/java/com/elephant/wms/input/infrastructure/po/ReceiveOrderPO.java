package com.elephant.wms.input.infrastructure.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("receive_order")
public class ReceiveOrderPO {
    private Long id;
    private String receiveCode;
    private String ownerCode;
    private String ownerName;
    private Integer actualQuantity;
    private String operatorName;
    private Long operatorId;
    private Integer status;
    private Integer standard;
    private String storageCode;
    private String extend; // JSON 数据可以使用 Map 存储
    private String createdTime;
    private String modifiedTime;
}

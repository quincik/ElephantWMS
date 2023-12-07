package com.elephant.wms.input.infrastructure.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("receive_notice")
public class ReceiveNoticePO {
    private Long id;
    private String receiveCode;
    private Integer type;
    private String ownerCode;
    private String contactsAddress;
    private String contactsPhone;
    private Integer quantity;
    private Integer actualQuantity;
    private String expectedArrivalDate;
    private Integer status;
    private String extend; // 可以使用 Map 存储 JSON 数据
    private String createdTime;
    private String modifiedTime;
}

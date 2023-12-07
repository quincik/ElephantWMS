package com.elephant.wms.input.infrastructure.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
@TableName("receive_notice")
public class ReceiveNoticePO {

    @TableId
    private Long id;
    private String code;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private String modifiedTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private String referenceCode;
}

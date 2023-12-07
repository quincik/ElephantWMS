package com.elephant.wms.input.infrastructure.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
@TableName("receive_order")
public class ReceiveOrderPO {

    @TableId
    private Long id;
    private String code;
    private String ownerCode;
    private Integer type;
    private Integer actualQuantity;
    private Long operatorId;
    private Integer status;
    private Integer standard;
    private String storageCode;
    private String extend;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private String createdTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private String modifiedTime;
}

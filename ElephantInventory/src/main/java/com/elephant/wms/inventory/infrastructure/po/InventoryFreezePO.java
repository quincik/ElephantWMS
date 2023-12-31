package com.elephant.wms.inventory.infrastructure.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
@TableName("inventory_freeze")
public class InventoryFreezePO {

    @TableId
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private String createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private String modifyTime;

    private Long detailId;
    private String referenceCode;
    private Integer type;
    private Integer requestQuantity;
    private Integer usedQuantity;
    private Integer status;
    private String idempotentKey;
    private String extend;
}

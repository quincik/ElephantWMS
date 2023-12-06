package com.elephant.wms.inventory.infrastructure.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
@TableName("inventory_details")
public class InventoryDetailPO {

    @TableId
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private String createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private String modifyTime;

    private Long itemBatch;
    private String itemCode;
    private Integer type;
    private String ownerCode;
    private String storageCode;
    private String areaCode;
    private Integer availableQuantity;
    private Integer frozenQuantity;
    private Integer standard;
}

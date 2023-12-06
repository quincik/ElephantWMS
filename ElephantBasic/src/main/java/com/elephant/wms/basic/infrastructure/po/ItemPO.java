package com.elephant.wms.basic.infrastructure.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
@TableName("basic_item")
public class ItemPO {

    @TableId
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private String createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")

    private String modifyTime;
    private String code;
    private Integer status;
    private String name;
    private String ownerCode;
    private Integer expiryDate;
    private Integer specs;
    private String extend;
    private Integer weight;
    private Integer length;
    private Integer height;
    private Integer width;
}

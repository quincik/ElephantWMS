package com.elephant.wms.basic.infrastructure.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
@TableName("basic_owner")
public class OwnerPO {

    @TableId
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private String createTime; // Changed to String
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private String modifyTime; // Changed to String

    private String code;
    private Integer status;
    private String name;
    private String contacts;
    private String contactsPhone;
    private String extend;
}

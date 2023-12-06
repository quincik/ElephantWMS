package com.elephant.wms.infrastructure.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
@TableName("basic_storage")
public class StoragePO {

    @TableId
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private String createTime; // Changed to String
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private String modifyTime; // Changed to String

    private String code;
    private Integer status;
    private Integer type;
    private String areaCode;
    private Integer length;
    private Integer width;
    private Integer height;
    private Integer maxWeight;
    private Integer sortIndex;
    private JsonNode extend;


}

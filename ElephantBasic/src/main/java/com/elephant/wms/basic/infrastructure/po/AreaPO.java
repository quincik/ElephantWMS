package com.elephant.wms.infrastructure.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.sql.Date;

@Data
@TableName("basic_area")
public class AreaPO {

    @TableId
    private Long id;

    private String code;
    private Integer type;
    private Integer length;
    private Integer height;
    private Integer width;
    private Integer status;
    private JsonNode extend;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private String createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private String modifyTime;

}

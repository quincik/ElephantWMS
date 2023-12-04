package com.elephant.wms.infrastructure.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("area")
public class AreaPO {

    @TableId
    private Long id;

    private String code;
    private Integer type;
    private Integer length;
    private Integer height;
    private Integer width;

}

package com.elephant.wms.input.infrastructure.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("receive_order_detail")
public class ReceiveOrderDetailPO {

    @TableId
    private Long id;
    private Long receiveOrderId;
    private String receiveOrderCode;
    private String ownerCode;
    private String ownerName;
    private String itemCode;
    private String itemName;
    private Long itemBatchId;
    private Integer actualQuantity;
    private Integer status;
    private String extend;
    private String createdTime;
    private String modifiedTime;
    private Integer standard;

}

package com.elephant.wms.input.infrastructure.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("receive_order_detail")
public class ReceiveOrderDetailPO {
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
    private String extend; // JSON 数据可以使用 Map 存储
    private String createdTime;
    private String modifiedTime;

}

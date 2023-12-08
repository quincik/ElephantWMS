package com.elephant.wms.input.core.enums;

import lombok.Getter;

public enum ReceiveOrderStatus {

    CREATE(0, "创建"),RECEIVING(10,"收货中"),COMPLETE(20,"完成");

    @Getter
    private Integer code;
    private String desc;

    ReceiveOrderStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public static ReceiveOrderStatus valueOf(Integer value) {
        for (ReceiveOrderStatus item : ReceiveOrderStatus.values()) {
            if(item.code == value) return item;
        }
        return null;
    }
}

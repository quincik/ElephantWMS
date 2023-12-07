package com.elephant.wms.input.core.enums;

import com.elephant.wms.inventory.core.enums.ScenarioEnum;
import lombok.Getter;

public enum ReceiveNoticeStatus {

    CREATE(0, "创建");

    @Getter
    private Integer code;
    private String desc;

    ReceiveNoticeStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public static ReceiveNoticeStatus valueOf(Integer value) {
        for (ReceiveNoticeStatus item : ReceiveNoticeStatus.values()) {
            if(item.code == value) return item;
        }
        return null;
    }
}

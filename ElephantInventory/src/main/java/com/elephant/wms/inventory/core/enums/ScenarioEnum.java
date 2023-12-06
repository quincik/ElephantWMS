package com.elephant.wms.inventory.core.enums;


import lombok.Getter;

public enum ScenarioEnum {

    RECEIVING(10, "收货入库");

    @Getter
    private Integer code;
    private String desc;

    ScenarioEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public static ScenarioEnum valueOf(Integer value) {
        for (ScenarioEnum item : ScenarioEnum.values()) {
            if(item.code == value) return item;
        }
        return null;
    }

}

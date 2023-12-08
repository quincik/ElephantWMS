package com.elephant.wms.common.infrastructure.enums;

import lombok.Getter;

/**
 * 错误码说明
 * IN - 代表入库，业务应用模块
 * 01 - 代表收货单，应用模块功能域
 * B  - 代表业务规则错误，错误类型
 * 111001 - 6位错误编码，1 收货单，1收货单明细，1扩展EXT，3位自定义码
 */
public enum INPUT_ERROR {

    // 收货单错误
    // 扩展错误
    IN01B111001("明细与收货单货主不一致"),
    IN01B111002("未找到对应收货单"),
    IN01B111003("收货单已完成，无法新增明细"),

    // 基础错误
    IN01B110001("收货明细数量不能少于 1"),

    // 通用错误
    IN00B0000001("货主下无此商品，请核实维护")
    ;

    @Getter
    private String desc;

    INPUT_ERROR(String desc) {
        this.desc = desc;
    }
}

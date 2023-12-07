package com.elephant.wms.basic.interfaces.service;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Optional;

public interface ItemService {

    @Data
    public static class ItemDTO{

        private String code;
        private Integer status;
        private String name;
        private String ownerCode;
        private Integer expiry;
        private Integer specs;
        private String extend;
        private Integer weight;
        private Integer length;
        private Integer height;
        private Integer width;
    }

    public Optional<ItemDTO> queryByCode(String code,String ownerCode);

}

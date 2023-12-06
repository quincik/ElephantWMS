package com.elephant.wms.basic.interfaces.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.util.Optional;

public interface StorageService {

    @Data
    public static class StorageDTO{
        private Long id;
        private String createTime;
        private String modifyTime;
        private String code;
        private Integer status;
        private Integer type;
        private String areaCode;
        private Integer length;
        private Integer width;
        private Integer height;
        private Integer maxWeight;
        private Integer sortIndex;
    }

    public Optional<StorageDTO> queryByCode(String code);
}

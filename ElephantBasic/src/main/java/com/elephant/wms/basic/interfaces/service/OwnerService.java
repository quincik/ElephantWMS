package com.elephant.wms.basic.interfaces.service;

import lombok.Data;

import java.util.Optional;

public interface OwnerService {

    @Data
    public static class OwnerDTO{

        private String code;
        private Integer status;
        private String name;
        private String contacts;
        private String contactsPhone;
        private String extend;
    }

    public Optional<OwnerDTO> queryByCode(String code);
}

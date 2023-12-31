package com.elephant.wms.basic.interfaces.service;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Map;
import java.util.Optional;

public interface ItemBatchService {

    @Data
    public static class ItemBatchDTO{
        private Long id;
        private String createTime;
        private String modifyTime;
        private String itemCode;
        private String ownerCode;
        private String manufacturingDate;
        private String receiptDate;
        private String basicItemBatchCol;
        private String referenceCode;
    }

    public Optional<ItemBatchDTO> queryById(Long id);
    public Optional<Long> create(Map<String, Object> param);

}

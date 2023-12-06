package com.elephant.wms.basic.interfaces.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.elephant.wms.basic.infrastructure.mapper.StorageMapper;
import com.elephant.wms.common.infrastructure.object.Result;
import com.elephant.wms.basic.infrastructure.po.StoragePO;
import com.elephant.wms.common.infrastructure.template.rest.BasicRest;
import com.elephant.wms.basic.interfaces.rest.convert.RestConvert;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.Data;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/storage")
public class StorageRest extends BasicRest {



    @Data
    public static class StorageVO{

        private Long id;
        private String createTime; // Changed to String
        private String modifyTime; // Changed to String
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

    @Resource
    StorageMapper storageMapper;

    @Resource
    CamelContext context;

    @PostMapping("/query")
    public Result<List<StorageVO>> query(@RequestBody  Map<String, Object> param) {

        IPage<StoragePO> areas = super.query(param, StoragePO.class);
        Result<List<StorageVO>> result = new Result<>(areas.getCurrent(),areas.getSize(),areas.getTotal());
        result.setData(RestConvert.INSTANCE.toStorageVO(areas.getRecords()));

        return  result;
    }

    @PostMapping("/create")
    public Result<Boolean> create(@RequestBody Map<String, Object> param){

        ProducerTemplate template = context.createProducerTemplate();
        Result<Boolean> result = (Result<Boolean>)
                template.requestBody("direct:createStorage", param);

        return result;
    }

    @Override
    @PostConstruct
    protected void init() {
        mapper = storageMapper;
    }
}

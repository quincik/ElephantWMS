package com.elephant.wms.basic.interfaces.rest;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.elephant.wms.common.infrastructure.template.rest.BasicRest;
import com.elephant.wms.basic.interfaces.rest.convert.RestConvert;
import com.elephant.wms.basic.infrastructure.mapper.ItemMapper;
import com.elephant.wms.common.infrastructure.object.Result;
import com.elephant.wms.basic.infrastructure.po.ItemPO;
import com.fasterxml.jackson.databind.JsonNode;

import jakarta.annotation.Resource;
import lombok.Data;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/item")
public class ItemRest extends BasicRest {


    @Override
    protected BaseMapper getMapper() {
        return itemMapper;
    }

    @Data
    public static class ItemVO{

        private Long id;
        private String createTime;
        private String modifyTime;
        private String code;
        private Integer status;
        private String name;
        private String ownerCode;
        private Integer expiry;
        private Integer specs;
        private Integer weight;
        private Integer length;
        private Integer height;
        private Integer width;
    }

    @Resource
    ItemMapper itemMapper;

    @Produce
    ProducerTemplate producerTemplate;

    @PostMapping("/query")
    public Result<List<ItemVO>> query(@RequestBody  Map<String, Object> param) {

        IPage<ItemPO> areas = super.query(param, ItemPO.class);
        Result<List<ItemVO>> result = new Result<>(areas.getCurrent(),areas.getSize(),areas.getTotal());
        result.setData(RestConvert.INSTANCE.toItemVO(areas.getRecords()));

        return  result;
    }

    @PostMapping("/create")
    public Result<Boolean> create(@RequestBody Map<String, Object> param){

        Result<Boolean> result = (Result<Boolean>)
                producerTemplate.requestBody("direct:createItem", param);
        return result;
    }

}

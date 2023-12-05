package com.elephant.wms.interfaces.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.elephant.wms.infrastructure.mapper.ItemMapper;
import com.elephant.wms.infrastructure.object.Result;
import com.elephant.wms.infrastructure.po.ItemPO;
import com.elephant.wms.infrastructure.template.rest.BasicRest;
import com.elephant.wms.interfaces.rest.convert.Convert;
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
@RequestMapping("/item")
public class ItemRest extends BasicRest {



    @Data
    public static class ItemVO{

        private Long id;
        private String createTime;
        private String modifyTime;
        private String code;
        private Integer status;
        private String name;
        private String ownerCode;
        private Integer expiryDate;
        private Integer specs;
        private JsonNode extend;
        private Integer weight;
        private Integer length;
        private Integer height;
        private Integer width;
    }

    @Resource
    ItemMapper itemMapper;

    @Resource
    CamelContext context;

    @PostMapping("/query")
    public Result<List<ItemVO>> query(@RequestBody  Map<String, Object> param) {

        IPage<ItemPO> areas = super.query(param, ItemPO.class);
        Result<List<ItemVO>> result = new Result<>(areas.getCurrent(),areas.getSize(),areas.getTotal());
        result.setData(Convert.INSTANCE.toItemVO(areas.getRecords()));

        return  result;
    }

    @PostMapping("/create")
    public Result<Boolean> create(@RequestBody Map<String, Object> param){

        ProducerTemplate template = context.createProducerTemplate();
        Result<Boolean> result = (Result<Boolean>)
                template.requestBody("direct:createItem", param);

        return result;
    }

    @Override
    @PostConstruct
    protected void init() {
        mapper = itemMapper;
    }
}

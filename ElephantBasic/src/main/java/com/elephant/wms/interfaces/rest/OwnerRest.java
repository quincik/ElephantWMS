package com.elephant.wms.interfaces.rest;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.elephant.wms.infrastructure.mapper.OwnerMapper;
import com.elephant.wms.infrastructure.object.Result;
import com.elephant.wms.infrastructure.po.OwnerPO;
import com.elephant.wms.infrastructure.template.rest.BasicRest;
import com.elephant.wms.interfaces.rest.convert.Convert;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@RequestMapping("/owner")
public class OwnerRest extends BasicRest {



    @Data
    public static class OwnerVO{
        private Long id;
        private String createTime; // Changed to String
        private String modifyTime; // Changed to String

        private String code;
        private Integer status;
        private String name;
        private String contacts;
        private String contactsPhone;
        private JsonNode extend;
    }

    @Resource
    OwnerMapper ownerMapper;

    @Resource
    CamelContext context;

    @PostMapping("/query")
    public Result<List<OwnerVO>> query(@RequestBody  Map<String, Object> param) {

        IPage<OwnerPO> areas = super.query(param, OwnerPO.class);
        Result<List<OwnerVO>> result = new Result<>(areas.getCurrent(),areas.getSize(),areas.getTotal());
        result.setData(Convert.INSTANCE.toOwnerVO(areas.getRecords()));

        return  result;
    }

    @PostMapping("/create")
    public Result<Boolean> create(@RequestBody Map<String, Object> param){

        ProducerTemplate template = context.createProducerTemplate();
        Result<Boolean> result = (Result<Boolean>)
                template.requestBody("direct:createOwner", param);

        return result;
    }

    @Override
    @PostConstruct
    protected void init() {
        mapper = ownerMapper;
    }
}

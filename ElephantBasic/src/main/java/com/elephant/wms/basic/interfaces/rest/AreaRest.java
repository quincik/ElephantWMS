package com.elephant.wms.interfaces.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.elephant.wms.infrastructure.mapper.AreaMapper;
import com.elephant.wms.infrastructure.object.Result;
import com.elephant.wms.infrastructure.po.AreaPO;
import com.elephant.wms.infrastructure.template.rest.BasicRest;
import com.elephant.wms.interfaces.rest.convert.RestConvert;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.Data;
import org.apache.camel.CamelContext;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/area")
public class AreaRest extends BasicRest {

    @Data
    public static class AreaVO{

        private Long id;
        private String code;
        private Integer type;
        private Integer length;
        private Integer height;
        private Integer width;
        private Integer status;
        private String createTime;
        private String modifyTime;
    }

    @Resource
    AreaMapper areaMapper;

    @Produce
    ProducerTemplate producerTemplate;

    @PostMapping("/query")
    public Result<List<AreaVO>> query(@RequestBody  Map<String, Object> param) {

        IPage<AreaPO> areas = super.query(param, AreaPO.class);
        Result<List<AreaVO>> result = new Result<>(areas.getCurrent(),areas.getSize(),areas.getTotal());
        result.setData(RestConvert.INSTANCE.toAreaVO(areas.getRecords()));

        return  result;
    }

    @PostMapping("/create")
    public Result<Boolean> create(@RequestBody Map<String, Object> param){
        Result<Boolean> result = (Result<Boolean>)
                producerTemplate.requestBody("direct:createArea", param);
        return result;
    }

    @Override
    @PostConstruct
    protected void init() {
        mapper = areaMapper;
    }
}

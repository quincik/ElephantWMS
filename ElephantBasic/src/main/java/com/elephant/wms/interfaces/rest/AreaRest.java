package com.elephant.wms.interfaces.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.elephant.wms.infrastructure.mapper.AreaMapper;
import com.elephant.wms.infrastructure.po.AreaPO;
import com.elephant.wms.interfaces.rest.convert.Convert;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.Data;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elephant.wms.infrastructure.common.Result;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/area")
public class AreaRest extends BasicRest{

    @Data
    public static class AreaVO{

        private Long id;
        private String code;
        private Integer type;
        private Integer length;
        private Integer height;
        private Integer width;
    }

    @Resource
    AreaMapper areaMapper;

    @Resource
    CamelContext context;

    @PostMapping("/query")
    public Result<List<AreaVO>> query(@RequestBody  Map<String, Object> param) {

        IPage<AreaPO> areas = super.query(param, AreaPO.class);
        Result<List<AreaVO>> result = new Result<>(areas.getCurrent(),areas.getSize(),areas.getTotal());
        result.setData(Convert.INSTANCE.toVO(areas.getRecords()));

        return  result;
    }

    @PostMapping("/create")
    public Result<Boolean> create(@RequestBody Map<String, Object> param){

        ProducerTemplate template = context.createProducerTemplate();
        Result<Boolean> result = (Result<Boolean>)
                template.requestBody("direct:createArea", param);

        return result;
    }

    @PostConstruct
    public void init(){
        setMapper(areaMapper);
    }
}

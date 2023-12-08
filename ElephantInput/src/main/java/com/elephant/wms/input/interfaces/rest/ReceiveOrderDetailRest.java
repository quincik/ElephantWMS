package com.elephant.wms.input.interfaces.rest;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elephant.wms.common.infrastructure.object.Result;
import com.elephant.wms.common.infrastructure.template.rest.BasicRest;
import com.elephant.wms.input.infrastructure.mapper.ReceiveOrderDetailMapper;
import jakarta.annotation.Resource;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("receive/order/detail")
public class ReceiveOrderDetailRest extends BasicRest {

    @Resource
    ReceiveOrderDetailMapper receiveOrderDetailMapper;

    @Produce
    ProducerTemplate producerTemplate;

    @Override
    protected BaseMapper getMapper() {
        return receiveOrderDetailMapper;
    }

    @PostMapping("/create")
    public Result<Boolean> create(@RequestBody Map<String, Object> param){

        Result<Boolean> result = (Result<Boolean>)
                producerTemplate.requestBody("direct:createReceiveOrder", param);
        return result;
    }
}

package com.elephant.wms.core.component.area;

import com.alibaba.fastjson2.JSONObject;
import com.elephant.wms.infrastructure.po.AreaPO;
import com.elephant.wms.infrastructure.template.compnent.Parse;
import jakarta.annotation.PostConstruct;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SimpleAreaParse extends Parse<AreaPO> {
    @Override
    @PostConstruct
    protected void init() {
        type = AreaPO.class;
    }
}

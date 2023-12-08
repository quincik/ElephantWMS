package com.elephant.app;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(scanBasePackages = {
        "com.elephant.wms.basic.interfaces",
        "com.elephant.wms.basic.core.component",
        "com.elephant.wms.input.interfaces",
        "com.elephant.wms.input.core.component",
        "com.elephant.wms.input.core.domain",
        "com.elephant.wms.inventory.interfaces",
        "com.elephant.wms.inventory.core.component",
    }
)
@MapperScan({
        "com.elephant.wms.basic.infrastructure.mapper",
        "com.elephant.wms.inventory.infrastructure.mapper",
        "com.elephant.wms.input.infrastructure.mapper"
})
@ImportResource(locations = {
        "classpath:camel.xml",
        "classpath:/mapper/inventory_details.xml",
        "classpath:/mapper/receive_order.xml"
    }
)
@OpenAPIDefinition(info = @Info(title = "Elephant WMS API", version = "1.0", description = "Elephant WMS 接口相关信息"))
public class ElephantApp {

    public static void main(String[] args) {
        SpringApplication.run(ElephantApp.class, args);
    }

}
package com.elephant.wms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@MapperScan("com.elephant.wms.infrastructure.mapper")
@ImportResource(locations = {"classpath:camel.xml","classpath:/mapper/*.xml"})
public class ElephantApp {

    public static void main(String[] args) {
        SpringApplication.run(ElephantApp.class, args);
    }

}
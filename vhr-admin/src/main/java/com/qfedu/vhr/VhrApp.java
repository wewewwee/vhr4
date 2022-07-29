package com.qfedu.vhr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.qfedu.vhr.framework.mapper","com.qfedu.vhr.system.mapper"})
public class VhrApp {
    public static void main(String[] args) {
        SpringApplication.run(VhrApp.class, args);
    }
}

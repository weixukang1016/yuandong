package com.pvsoul.eec.yuandong;

import org.glassfish.jersey.server.ResourceConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.pvsoul.eec.yuandong.mapper")
public class YuandongApplication {

    public static void main(String[] args) {
        SpringApplication.run(YuandongApplication.class, args);
    }

    @Bean
    public ResourceConfig resourceConfig() {
        return new ResourceConfig();
    }
}

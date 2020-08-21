package com.person.target.springframedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient //cloud E版本后该注解可以去掉
@MapperScan("com.person.target.springframedemo.mapper.auto")
public class SpringFrameDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringFrameDemoApplication.class, args);
    }

}

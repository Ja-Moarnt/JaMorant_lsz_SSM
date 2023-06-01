package com.JaMorant.SSM.thc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.JaMorant.SSM.thc.mapper")
@EnableFeignClients(basePackages = "com.JaMorant")
@ComponentScan(basePackages = "com.JaMorant")
@EnableAspectJAutoProxy  //APO
public class ServiceThcApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceThcApplication.class, args);
    }
}

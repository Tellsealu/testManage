package com.njust;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author qufeng
 * @Date 2021/2/3 15:29
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.njust.mapper"})
@EnableDubbo
public class ResourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(com.njust.ResourceApplication.class, args);
        System.out.println("资源子系统启动成功");

    }
}
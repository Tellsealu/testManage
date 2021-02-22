package com.njust;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @Author qufeng
 * @Date 2021/2/1 19:55
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.njust.mapper"})
@EnableDubbo
public class QuestionApplication {
    public static void main(String[] args) {
        SpringApplication.run(com.njust.QuestionApplication.class, args);
        System.out.println("题库管理子系统启动成功");

    }
}

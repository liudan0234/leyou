package com.leyou.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

//启动类
@SpringBootApplication
@EnableZuulProxy
public class LyGateway {
    public static void main(String[] args) {
        SpringApplication.run(LyGateway.class,args);
    }
}

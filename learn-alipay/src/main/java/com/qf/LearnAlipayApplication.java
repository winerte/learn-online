package com.qf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: yanjia
 * @DATE: 2021/1/12 10:19
 */
@SpringBootApplication
@EnableDiscoveryClient
public class LearnAlipayApplication {
    public static void main(String[] args) {
        SpringApplication.run(LearnAlipayApplication.class);
    }
}

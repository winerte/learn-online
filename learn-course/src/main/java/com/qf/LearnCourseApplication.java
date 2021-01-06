package com.qf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: yanjia
 * @DATE: 2021/1/5 20:55
 */
@SpringBootApplication
@EnableDiscoveryClient
public class LearnCourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnCourseApplication.class);
    }

}

package com.qf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class)
@EnableEurekaClient
@EnableDiscoveryClient
public class LearnZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(LearnZuulApplication.class);
    }
}

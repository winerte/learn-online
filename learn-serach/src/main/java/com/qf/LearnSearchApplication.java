package com.qf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Service;
//(exclude =  org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class)
@SpringBootApplication(exclude =  org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
public class LearnSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(LearnSearchApplication.class);
    }
}

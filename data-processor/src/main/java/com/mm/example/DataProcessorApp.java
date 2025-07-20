package com.mm.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@ComponentScan(basePackages = {
        "com.mm.example.dbOne.config",
        "com.mm.example.dbTwo.config"
})
public class DataProcessorApp {
    public static void main(String[] args) {
       SpringApplication.run(DataProcessorApp.class, args);
    }
}
package com.devloper.joker.mybatispluscascade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MybatisPlusCascadeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusCascadeApplication.class, args);
    }
}

package com.macro.blog.storage;

import org.dromara.x.file.storage.spring.EnableFileStorage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableFileStorage
@SpringBootApplication
public class SpringFileStorageApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringFileStorageApplication.class, args);
    }
}

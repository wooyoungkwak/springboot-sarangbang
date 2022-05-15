package com.young.sarangbang;

import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@Configuration
@EnableAutoConfiguration
@EntityScan(
        basePackageClasses = {Jsr310JpaConverters.class},
        basePackages = {"com.young.sarangbang.model"}
)
@ComponentScan(basePackages = {"com.young"}, excludeFilters = {@Filter(SpringBootApplication.class)})
public class SarangbangApplicationTests {

    public static void main(String[] args) {
        SpringApplication.run(SarangbangApplicationTests.class, args);
    }

}


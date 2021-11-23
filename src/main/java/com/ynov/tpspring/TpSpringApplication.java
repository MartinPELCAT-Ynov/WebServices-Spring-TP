package com.ynov.tpspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Configuration
@EntityScan("com.ynov.tpspring.entities")
@EnableJpaRepositories("com.ynov.tpspring.repoitories")
public class TpSpringApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(TpSpringApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(TpSpringApplication.class);
    }
}
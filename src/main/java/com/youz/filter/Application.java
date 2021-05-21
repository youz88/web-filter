package com.youz.filter;

import com.youz.filter.web.FilterPointcutAdvisor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    @Bean
    public FilterPointcutAdvisor filterPointcutAdvisor() {
        return new FilterPointcutAdvisor("com.youz.filter.web")
                .addPathPatterns("/**")
                .excludePathPatterns("");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}

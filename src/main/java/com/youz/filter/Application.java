package com.youz.filter;

import com.youz.filter.enums.FilterType;
import com.youz.filter.filter.FilterPointcutAdvisor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;

@SpringBootApplication
public class Application {

    @Bean
    public FilterPointcutAdvisor filterPointcutAdvisor() {
        FilterPointcutAdvisor advisor = new FilterPointcutAdvisor("com.youz.filter.web")
                .addFilter(FilterType.Emoji.newInstance().includePatterns("/**").excludePatterns("").allowedMethods("GET","POST"))
                .addFilter(FilterType.Replace.newInstance(new HashMap(){{put("a","11111111");}}).includePatterns("/**").excludePatterns(""))
                ;
        return advisor;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}

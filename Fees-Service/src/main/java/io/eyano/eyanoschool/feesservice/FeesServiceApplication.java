package io.eyano.eyanoschool.feesservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class FeesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeesServiceApplication.class, args);
    }
    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();

    }

}

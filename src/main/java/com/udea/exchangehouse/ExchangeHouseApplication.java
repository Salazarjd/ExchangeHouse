package com.udea.exchangehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ExchangeHouseApplication {

    @GetMapping("/hello")
    public String hello(){
        return "Hola ciclo 3..... saldremos vivos de esto";
    }

    public static void main(String[] args) {
        SpringApplication.run(ExchangeHouseApplication.class, args);
    }

}

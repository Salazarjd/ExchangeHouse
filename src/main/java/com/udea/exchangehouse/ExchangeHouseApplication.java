package com.udea.exchangehouse;

import com.udea.exchangehouse.models.Empresa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ExchangeHouseApplication {

    @GetMapping("/hello")
    public String hello(){
        return "verEmpresas";
    }

    @GetMapping("/test")
    public String test(){
        Empresa emp = new Empresa("Solar SAS", "Calle larga", "3133987877", "89912231-1");
        emp.setNombre("Solar LTDA");
        return emp.getNombre();
    }

    public static void main(String[] args) {
        SpringApplication.run(ExchangeHouseApplication.class, args);
    }

}

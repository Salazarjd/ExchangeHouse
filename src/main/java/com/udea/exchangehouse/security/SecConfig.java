package com.udea.exchangehouse.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder()).dataSource(dataSource)
                .usersByUsernameQuery("select email, password, estado from empleado where email=?")
                .authoritiesByUsernameQuery("select email, rol from empleado where email=?");
    }

    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().anyRequest().authenticated().and().formLogin().permitAll().and().logout().permitAll();
    }
}

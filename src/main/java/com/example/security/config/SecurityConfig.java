package com.example.security.config;

import com.example.security.service.UserDetailSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailSecurityService service;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        //.requestMatchers("/livre").permitAll()
                        .requestMatchers(HttpMethod.POST, "/new").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/edit").permitAll()
                        .anyRequest().authenticated());

        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    static PasswordEncoder psEncode()
    {
        return new BCryptPasswordEncoder();
    }


    protected void autheManager(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service);
    }

    /*@Bean
    UserDetailsService user()
    {
        UserDetails user = User.builder()
                .username("yoikagen")
                .password(psEncode().encode("tetragon"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }*/


}

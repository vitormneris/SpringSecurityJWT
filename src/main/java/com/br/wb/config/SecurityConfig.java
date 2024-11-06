package com.br.wb.config;

import com.br.wb.FilterToken;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final FilterToken filter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/contact").permitAll()
                        .requestMatchers(HttpMethod.POST, "/authenticate").permitAll()
                        .requestMatchers(HttpMethod.POST, "/clients").permitAll()
                        .requestMatchers("/", "/swagger-ui/**", "/swagger-ui/index.html#/","v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/processos/ultimomovimento").permitAll()
                        .requestMatchers(HttpMethod.GET, "/administrator/protected").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/administrators").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/administrator/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/administrator/email/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/administrator").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/administrator/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/administrator/**").hasRole("ADMIN")
                                       
                        .requestMatchers(HttpMethod.GET, "/installments").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/installments").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/installments/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/installments/dueDate/**").hasRole("ADMIN")
                                       
                        .anyRequest().authenticated())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
        }
}

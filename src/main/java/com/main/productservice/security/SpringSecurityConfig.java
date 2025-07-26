package com.main.productservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    @Bean
    public SecurityFilterChain filteringCriterion(HttpSecurity http) throws Exception {

            http.authorizeHttpRequests(auth -> auth
                            //.requestMatchers("/error").permitAll()
                            .requestMatchers("/products").hasAuthority("SCOPE_profile")
                            .anyRequest().authenticated()
                    )
                    .oauth2ResourceServer((oauth2) -> oauth2.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(new CustomJwtAuthenticationConverter())));
            return http.build();
        }

}

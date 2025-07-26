package com.main.productservice.security;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class TokenValidator {
    /* Calls User Service for token validation

     */
    private RestTemplateBuilder restTemplateBuilder;
    public TokenValidator(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }
    public Optional<JwtObject> validateToken(String token){
        RestTemplate restTemplate = restTemplateBuilder.build();
        return null;
    }
}

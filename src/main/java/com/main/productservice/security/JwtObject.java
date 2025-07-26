package com.main.productservice.security;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class JwtObject {
    private String email;
    private Date createdAt;
    private Date expiryAt;
    private List<Role> roles;
}

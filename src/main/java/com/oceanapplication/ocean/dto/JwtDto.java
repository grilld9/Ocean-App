package com.oceanapplication.ocean.dto;

import java.util.List;

public class JwtDto {

    public JwtDto(String token, Long id, String phoneNumber, List<Role> roles) {
        this.token = token;
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
    }

    private String token;
    private String type = "Bearer";
    private Long id;
    private String phoneNumber;
    private List<Role> roles;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}

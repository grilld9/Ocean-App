package com.oceanapplication.ocean.dto;

import com.oceanapplication.ocean.models.Role;
import com.oceanapplication.ocean.models.TokenType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtDto {

    private String token;
    private TokenType type = TokenType.BEARER;
    private Long id;
    private String phoneNumber;
    private List<Role> roles;
}

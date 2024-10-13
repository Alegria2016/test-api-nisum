package com.testnisum.api_rest.auth;

import com.testnisum.api_rest.models.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String name;
    private String lastName;
    private String email;
    private String password;
    private int status;
    private Role role;
}

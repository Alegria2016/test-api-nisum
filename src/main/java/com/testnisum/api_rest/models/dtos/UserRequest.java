package com.testnisum.api_rest.models.dtos;

import com.testnisum.api_rest.models.entities.Phone;
import com.testnisum.api_rest.models.enums.Role;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private UUID id;

    private String name;
    @NotEmpty(message = "El cooreo debe cumplir con el formato ")
    private String email;
    private String password;
    private List<PhoneRequest> phones;
    private String token;
    private Role role;
    private boolean isactive;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;

}

package com.testnisum.api_rest.models.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterResponse extends ApiResponse<RegisterResponse> {
    private UUID id;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private String token;
    private boolean isactive;

    @Override
    public RegisterResponse buildSuccessResponse() {
        this.setValid(Boolean.TRUE);
        this.setStatusCode(HttpStatus.OK.value());
        this.setMessage("Ok ");
        return this;
    }

    @Override
    public <X extends Exception> RegisterResponse buildErrorResponse(X e) {
        this.setValid(Boolean.FALSE);
        this.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        this.setMessage("Ocurrió un error ");
        return this;
    }
}

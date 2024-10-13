package com.testnisum.api_rest.models.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.testnisum.api_rest.models.entities.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse  extends ApiResponse<UserResponse> {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private List<PhoneResponse> phones;


    @Override
    public UserResponse buildSuccessResponse() {
        this.setValid(Boolean.TRUE);
        this.setStatusCode(HttpStatus.OK.value());
        this.setMessage("Ok ");
        return this;
    }

    @Override
    public <X extends Exception> UserResponse buildErrorResponse(X e) {
        this.setValid(Boolean.FALSE);
        this.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        this.setMessage("Ocurrió un error ");
        return this;
    }
}

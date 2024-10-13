package com.testnisum.api_rest.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class ApiResponse <T> implements Serializable {

    private Boolean valid;
    private Integer statusCode;
    private String message;


    public abstract T buildSuccessResponse();

    public abstract <X extends Exception> T buildErrorResponse(X e);
}

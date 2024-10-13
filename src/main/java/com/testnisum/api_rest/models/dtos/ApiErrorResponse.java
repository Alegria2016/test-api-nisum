package com.testnisum.api_rest.models.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class ApiErrorResponse {
    private String code;
    private HttpStatus status;
    private String message;
    private List<String> detailMessage;
    private LocalDate timeStamp;
}

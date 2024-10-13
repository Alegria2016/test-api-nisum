package com.testnisum.api_rest.models.dtos;

import com.testnisum.api_rest.models.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneResponse {
    private Long id;
    private String number;
    private String cityCode;
    private String countryCode;

}

package com.testnisum.api_rest.services;

import static org.mockito.BDDMockito.given;

import com.testnisum.api_rest.auth.AuthenticationRequest;
import com.testnisum.api_rest.auth.AuthenticationResponse;
import com.testnisum.api_rest.auth.AuthenticationServiceImpl;
import com.testnisum.api_rest.models.dtos.*;
import com.testnisum.api_rest.models.entities.User;
import com.testnisum.api_rest.repositories.UserRepository;
import com.testnisum.api_rest.services.impl.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AuthenticationServiceTest {


    AuthenticationServiceImpl authenticationService = Mockito.mock(AuthenticationServiceImpl.class);
    private UUID uuid;

    @BeforeEach
    void setUp(){
        uuid = uuidGenerator();

    }

    @DisplayName("Test para registra un Usuario.")
    @Test
    void registerTest(){
        Mockito.when(authenticationService.register(mockUserRequest())).thenReturn(generateRegisterResponse());
        RegisterResponse response = authenticationService.register(mockUserRequest());
        Assertions.assertThat(response).isNotNull();
        System.out.println(response.getToken());


    }


    @DisplayName("Test para autenticar un Usuario.")
    @Test
    void loginTest(){
        Mockito.when(authenticationService.login(generateAuthRequest())).thenReturn(generateAuthResponse());
        AuthenticationResponse response = authenticationService.login(generateAuthRequest());
        Assertions.assertThat(response).isNotNull();
        System.out.println(response.getToken());

    }




    UUID uuidGenerator(){
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        return uuid;

    }

    List<PhoneResponse> phoneResponses(){
        List<PhoneResponse>  phones = new ArrayList<>();
        PhoneResponse p1 = PhoneResponse.builder()
                .id(1L)
                .cityCode("01")
                .countryCode("57")
                .build();

        PhoneResponse p2 = PhoneResponse.builder()
                .id(2L)
                .cityCode("02")
                .countryCode("55")
                .build();

        phones.add(p1);
        phones.add(p2);
        return phones;
    }

    List<PhoneRequest> phoneRequest(){
        List<PhoneRequest>  phones = new ArrayList<>();
        PhoneRequest p1 = PhoneRequest.builder()
                .id(1L)
                .cityCode("01")
                .countryCode("57")
                .build();

        PhoneRequest p2 = PhoneRequest.builder()
                .id(2L)
                .cityCode("02")
                .countryCode("55")
                .build();

        phones.add(p1);
        phones.add(p2);
        return phones;
    }



    UserResponse mockUserResponse() {
        return UserResponse.builder()
                .id(uuid)
                .name("PEDRO")
                .email("test@gmail.com")
                .password("Test123=")
                .phones(phoneResponses())
                .build();

    }

    UserRequest mockUserRequest() {
        return UserRequest.builder()
                .id(uuid)
                .name("PEDRO")
                .email("test@gmail.com")
                .password("Test123=")
                .phones(phoneRequest())
                .build();

    }

    RegisterResponse generateRegisterResponse(){
        return RegisterResponse.builder()
                .id(uuid)
                .token("eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiVVNFUiIsIm5hbWUiOiJKdWFuIiwic3ViIjoianVhbjFAZ21haWwuY29tIiwiaWF0IjoxNzI4OTQxODgyLCJleHAiOjE3Mjg5NDM2ODJ9.baZyzMo8g7UjXRDr7ea-ihMr-r2FWleLJwCMWp5LfFY")
                .isactive(Boolean.TRUE)
                .created(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .build();
    }


    AuthenticationRequest generateAuthRequest(){
        return AuthenticationRequest.builder()
                .email("test@gmail.com")
                .password("Test123=")
                .build();
    }



    AuthenticationResponse generateAuthResponse(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiVVNFUiIsIm5hbWUiOiJKdWFuIiwic3ViIjoianVhbjFAZ21haWwuY29tIiwiaWF0IjoxNzI4OTQxODgyLCJleHAiOjE3Mjg5NDM2ODJ9.baZyzMo8g7UjXRDr7ea-ihMr-r2FWleLJwCMWp5LfFY";
        return new AuthenticationResponse(
                token,true,mockUserResponse()

        );
    }


}

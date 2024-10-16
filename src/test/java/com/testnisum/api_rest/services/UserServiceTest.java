package com.testnisum.api_rest.services;

import com.testnisum.api_rest.models.dtos.PhoneRequest;
import com.testnisum.api_rest.models.dtos.PhoneResponse;

import com.testnisum.api_rest.models.dtos.UserRequest;
import com.testnisum.api_rest.models.dtos.UserResponse;


import com.testnisum.api_rest.services.impl.UserServiceImpl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class UserServiceTest {

    UserServiceImpl userRepository = Mockito.mock(UserServiceImpl.class);
    private UUID uuid;


    @BeforeEach
    void setUp(){
        uuid = uuidGenerator();


    }

    @DisplayName("Test para buscar usuario por UUID.")
    @Test
    void findUserById() {

     UUID uuid = uuidGenerator();
     Mockito.when(userRepository.findUserById(uuid)).thenReturn(mockUserResponse());

     UserResponse response = userRepository.findUserById(uuid);
        Assertions.assertThat(response).isNotNull();
        System.out.println(response.getEmail());



    }


    @DisplayName("Test para actualizar usuario.")
    @Test
    void updateTest() {

        Mockito.when(userRepository.update(uuid, mockUserRequest())).thenReturn(mockUserResponse());
        UserResponse response = userRepository.update(uuid, mockUserRequest());
        Assertions.assertThat(response).isNotNull();
        System.out.println(response.getName());
    }

    @DisplayName("Test para eliminar usuario.")
    @Test
    void deleteByIdTest() {
        UUID uuid = uuidGenerator();
        Mockito.when(userRepository.deleteById(uuid)).thenReturn(mockUserResponse());
        UserResponse response = userRepository.deleteById(uuid);
        Assertions.assertThat(response).isNotNull();
        System.out.println(response.getEmail());
    }



    UUID uuidGenerator(){
        return  UUID.randomUUID();
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

}
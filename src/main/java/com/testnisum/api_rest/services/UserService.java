package com.testnisum.api_rest.services;

import com.testnisum.api_rest.models.dtos.RegisterResponse;
import com.testnisum.api_rest.models.dtos.UserRequest;
import com.testnisum.api_rest.models.dtos.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {

    UserResponse findUserById(UUID id);
    Page<UserResponse> findAll(Pageable pageable);
    UserResponse update(UUID id, UserRequest request);
    UserResponse deleteById(UUID id);

}

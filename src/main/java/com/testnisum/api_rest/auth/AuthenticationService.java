package com.testnisum.api_rest.auth;

import com.testnisum.api_rest.models.dtos.RegisterResponse;
import com.testnisum.api_rest.models.dtos.UserRequest;

public interface AuthenticationService {

    RegisterResponse register(UserRequest request);

    AuthenticationResponse login(AuthenticationRequest authenticationRequest);
}

package com.testnisum.api_rest.auth;

import com.testnisum.api_rest.config.JwtService;
import com.testnisum.api_rest.models.dtos.*;
import com.testnisum.api_rest.models.entities.Phone;
import com.testnisum.api_rest.models.entities.User;
import com.testnisum.api_rest.models.enums.Role;
import com.testnisum.api_rest.repositories.PhoneRepository;
import com.testnisum.api_rest.repositories.UserRepository;
import com.testnisum.api_rest.utils.Constant;
import com.testnisum.api_rest.utils.ErrorCatalog;
import com.testnisum.api_rest.utils.Util;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service

public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    private final PhoneRepository phoneRepository;

    private final PasswordEncoder passwordEncoder;

    public static final ModelMapper modelMapper = new ModelMapper();

    private final Util util;


    public AuthenticationServiceImpl(PhoneRepository phoneRepository, PasswordEncoder passwordEncoder, Util util) {
        this.phoneRepository = phoneRepository;
        this.passwordEncoder = passwordEncoder;
        this.util = util;
    }


    public RegisterResponse register(UserRequest request){
        RegisterResponse response = new RegisterResponse();

        User userFound = userRepository.findByEmail(request.getEmail()).orElse(null);

        if(userFound  !=null){
            response.setMessage(ErrorCatalog.EXISTS_USER.getMessage());
            response.setStatusCode(Integer.valueOf(ErrorCatalog.EXISTS_USER.getCode()));
            response.setValid(Boolean.FALSE);
            return response;
        }

        var user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(Role.valueOf(Constant.USER_ROLE)); //
        user.setIsactive(Constant.IS_ACTIVE_TRUE);

        String token = jwtService.generateToken(user, generateExtraClaims(user));
        user.setToken(token);
        user.setCreated(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());

         util.validateDataUser(request);

        try {

                userRepository.save(user);

                if (!request.getPhones().isEmpty()) {
                    request.getPhones().forEach((p) -> {
                        Phone phone = Phone.builder()
                                .cityCode(p.getCityCode())
                                .number(p.getNumber())
                                .countryCode(p.getCountryCode())
                                .user(user)
                                .build();

                        phoneRepository.save(phone);

                    });

                }

                return modelMapper.map(user, RegisterResponse.class);

        }catch(final Exception e) {
            response.setMessage(ErrorCatalog.GENERIC_ERROR.getMessage());
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setValid(Boolean.FALSE);
            return response;
        }

    }




    public AuthenticationResponse login(AuthenticationRequest authenticationRequest){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(), authenticationRequest.getPassword()
        );
        authenticationManager.authenticate(authToken);
        User user = userRepository.findByEmail(authenticationRequest.getEmail()).get();
        String jwt = jwtService.generateToken(user, generateExtraClaims(user));
        boolean isSuccess = true;
        UserResponse u = modelMapper.map(user, UserResponse.class);
        return new AuthenticationResponse(jwt,isSuccess,u);
    }


   UserResponse getUserProfileByToken(String token){
      String email = jwtService.extractUsername(getTokenFormRequest(token));
       User user = userRepository.findByEmail(email).get();
       return  modelMapper.map(user, UserResponse.class);

   }




    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getName());
        extraClaims.put("role", user.getRole().name());
        return extraClaims;
    }


    private String getTokenFormRequest(String token) {

        if(StringUtils.hasText(token) && token.startsWith("Bearer ") ){
            return token.substring(7);

        }

        return null;

    }





}

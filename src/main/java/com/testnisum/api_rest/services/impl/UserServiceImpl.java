package com.testnisum.api_rest.services.impl;

import com.testnisum.api_rest.exceptions.UserNotFoundException;
import com.testnisum.api_rest.models.dtos.RegisterResponse;
import com.testnisum.api_rest.models.dtos.UserRequest;
import com.testnisum.api_rest.models.dtos.UserResponse;
import com.testnisum.api_rest.models.entities.Phone;
import com.testnisum.api_rest.models.entities.User;
import com.testnisum.api_rest.repositories.PhoneRepository;
import com.testnisum.api_rest.repositories.UserRepository;
import com.testnisum.api_rest.services.UserService;
import com.testnisum.api_rest.utils.Constant;
import com.testnisum.api_rest.utils.ErrorCatalog;
import com.testnisum.api_rest.utils.Util;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.catalog.CatalogException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PhoneRepository phoneRepository;

    public static final ModelMapper modelMapper = new ModelMapper();

    private final Util util;


    @Override
    public UserResponse findUserById(UUID id) {

        return userRepository.findById(id)
                .map(user -> modelMapper.map(user,UserResponse.class))
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Page<UserResponse> findAll(Pageable pageable) {
        int startIndex =(int) pageable.getOffset();

        List<User> users = userRepository.findAll();

        int endIndex = Math.min(startIndex + pageable.getPageSize(), users.size());

        List<UserResponse> pageContent = users.subList(startIndex,endIndex) .stream()
                .map(user -> modelMapper.map(user,UserResponse.class))
                .toList();

        return new PageImpl<>(pageContent, pageable,users.size());
    }

    @Override
    public UserResponse update(UUID id, UserRequest request) {
        return userRepository.findById(id)
                .map(user -> {
                       user.setId(user.getId());
                       user.setName(request.getName());
                       user.setEmail(request.getEmail());
                       user.setIsactive(request.isIsactive());
                       user.setRole(user.getRole());
                       user.setPassword(passwordEncoder.encode(request.getPassword()));
                       user.setModified(LocalDateTime.now());

                    if(!request.getPhones().isEmpty()){
                        request.getPhones().forEach((p)->{
                            Phone phone = Phone.builder()
                                    .id(p.getId())
                                    .cityCode(p.getCityCode())
                                    .number(p.getNumber())
                                    .countryCode(p.getCountryCode())
                                    .user(user)
                                    .build();
                            phoneRepository.save(phone);

                        });

                    }

                    return userRepository.save(user);



                }).map(user -> modelMapper.map(user,UserResponse.class))
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserResponse deleteById(UUID id) {
        UserResponse response = new UserResponse();

        if(userRepository.findById(id).isEmpty()){
            response.setMessage(ErrorCatalog.USER_NOT_FOUND.getMessage());
            response.setStatusCode(Integer.valueOf(ErrorCatalog.USER_NOT_FOUND.getCode()));
            response.setValid(Boolean.FALSE);

            return response;
        }


        userRepository.deleteById(id);

        response.setMessage(ErrorCatalog.USER_DELETED.getMessage());
        response.setStatusCode(HttpStatus.ACCEPTED.value());
        response.setValid(Boolean.TRUE);

        return response;
    }




}

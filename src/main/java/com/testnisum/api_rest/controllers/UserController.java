package com.testnisum.api_rest.controllers;

import com.testnisum.api_rest.models.dtos.UserRequest;
import com.testnisum.api_rest.models.dtos.UserResponse;
import com.testnisum.api_rest.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {


    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findUserById(@PathVariable UUID id){
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<Page<UserResponse>> findAll(@PageableDefault Pageable pageable){
        return new ResponseEntity<>(userService.findAll(pageable), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody UserRequest request ){
        return new ResponseEntity<>(userService.update(id,request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponse> deleteById(@PathVariable UUID id){
        return new ResponseEntity<>(userService.deleteById(id), HttpStatus.OK);
    }
}

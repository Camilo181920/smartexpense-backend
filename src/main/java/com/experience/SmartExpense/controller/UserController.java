package com.experience.SmartExpense.controller;


import com.experience.SmartExpense.dto.UserRequestDTO;
import com.experience.SmartExpense.entity.User;
import com.experience.SmartExpense.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {


    private final UserService service;


    public UserController(UserService service){
        this.service = service;
    }



    @GetMapping
    public List<User> getUsers(){

        return service.getUsers();

    }



    @PostMapping
    public User createUser(
            @Valid @RequestBody UserRequestDTO request
    ){


        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();


        return service.createUser(user);

    }

}
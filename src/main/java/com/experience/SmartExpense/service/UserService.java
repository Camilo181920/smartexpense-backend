package com.experience.SmartExpense.service;

import com.experience.SmartExpense.entity.User;

import java.util.List;

public interface UserService {


    User createUser(User user);


    List<User> getUsers();


    User getUserById(Long id);

    
    User getUserByEmail(String email);
}
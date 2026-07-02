package com.experience.SmartExpense.service.impl;

import com.experience.SmartExpense.exception.ResourceNotFoundException;
import com.experience.SmartExpense.entity.User;
import com.experience.SmartExpense.repository.UserRepository;
import com.experience.SmartExpense.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {


    private final UserRepository repository;


    public UserServiceImpl(UserRepository repository){
        this.repository = repository;
    }


    @Override
    public User createUser(User user){

        return repository.save(user);

    }


    @Override
    public List<User> getUsers(){

        return repository.findAll();

    }

    @Override
    public User getUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));
    }

}
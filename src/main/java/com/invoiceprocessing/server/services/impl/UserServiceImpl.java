package com.invoiceprocessing.server.services.impl;

import com.invoiceprocessing.server.dtos.UserDto;
import com.invoiceprocessing.server.exception.ResourceNotFound;
import com.invoiceprocessing.server.model.User;
import com.invoiceprocessing.server.repository.UserRepo;
import com.invoiceprocessing.server.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper model;


    @Override
    public UserDto createUser(UserDto userDto) {

        User user = model.map(userDto, User.class);
        User user1 = userRepo.save(user);
        return model.map(user1,UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "Id", userId));

        user.setUserId(userId);
        user.setUserName(userDto.getUserName());

        User user2 = userRepo.save(user);
        return model.map(user2, UserDto.class);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("Team", "Id", userId));
        return model.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(team -> model.map(team, UserDto.class)).collect(Collectors.toList());
        return userDtos;
    }
}

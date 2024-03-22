package com.invoiceprocessing.server.controller;

import com.invoiceprocessing.server.dtos.UserDto;
import com.invoiceprocessing.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    private ResponseEntity<?> createUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }
    @PutMapping("/{userId}")
    private ResponseEntity<?> updateUser(@RequestBody UserDto userDto, @PathVariable("userId") Integer userId){
        return new ResponseEntity<>(userService.updateUser(userDto, userId), HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    private ResponseEntity<?> getUserById(@PathVariable("userId") Integer userId){
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }
    @GetMapping("/")
    private ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }


}

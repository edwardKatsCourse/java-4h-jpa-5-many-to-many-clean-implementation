package com.telran.controller;

import com.telran.entity.User;
import com.telran.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users/{name}")
    public User save(@PathVariable("name") String name) {
        User user = User.builder()
                .name(name)
                .build();

        //+ user id
        return userRepository.save(user);
    }

    @GetMapping("/users/{id}")
    public User findById(@PathVariable("id") Long id) {
        return userRepository.findById(id)
                .orElse(null);
    }

    @GetMapping("/users")
    public List<User> findAll() {
        return userRepository.findAll();
    }

}

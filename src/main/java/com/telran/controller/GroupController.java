package com.telran.controller;

import com.telran.entity.Group;
import com.telran.entity.User;
import com.telran.repository.GroupRepository;
import com.telran.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    @PostMapping("/groups/{name}")
    public Group save(@PathVariable("name") String name) {
        Group user = Group.builder()
                .groupName(name)
                .build();

        //+ user id
        return groupRepository.save(user);
    }

    @GetMapping("/groups/{id}")
    public Group findById(@PathVariable("id") Long id) {
        return groupRepository.findById(id)
                .orElse(null);
    }

    @GetMapping("/groups")
    public List<Group> findAll() {
        return groupRepository.findAll();
    }
}

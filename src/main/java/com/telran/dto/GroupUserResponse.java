package com.telran.dto;

import com.telran.entity.User;

import java.util.List;

public class GroupUserResponse {

    private Long groupId;
    private String groupName;
    private List<User> usersInGroup;
}

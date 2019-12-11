package com.telran.controller;

import com.telran.dto.GroupUserResponse;
import com.telran.dto.UserGroupResponse;
import com.telran.entity.Group;
import com.telran.entity.User;
import com.telran.entity.UserGroup;
import com.telran.repository.GroupRepository;
import com.telran.repository.UserGroupRepository;
import com.telran.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserGroupController {

    private UserGroupRepository userGroupRepository;
    private UserRepository userRepository;
    private GroupRepository groupRepository;

    @Autowired
    public UserGroupController(UserGroupRepository userGroupRepository, UserRepository userRepository, GroupRepository groupRepository) {
        this.userGroupRepository = userGroupRepository;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    @GetMapping("/users/{id}/groups")
    public UserGroupResponse findGroupsByUser(@PathVariable("id") Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }

        List<UserGroup> userGroups = userGroupRepository.findAllByUser(user);
        List<Group> groups = userGroups
                .stream()
                .map(UserGroup::getGroup)
                .collect(Collectors.toList());

        return UserGroupResponse.builder()
                .userId(user.getId())
                .userName(user.getName())
                .userGroups(groups)
                .build();

    }

//    @GetMapping("/groups/{id}/users")
//    public GroupUserResponse findUsersByGroup(@PathVariable("id") Long groupId) {
//
//    }


    @PutMapping("/users/{user_id}/groups/{group_id}")
    public void addGroupToUser(@PathVariable("user_id") Long userId, @PathVariable("group_id") Long groupId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            String errorMessage = String.format("User with such ID %s - does not exist", userId);
            throw new RuntimeException(errorMessage);
        }

        Group group = groupRepository.findById(groupId).orElse(null);
        if (group == null) {
            String errorMessage = String.format("Group with such ID %s - does not exist", groupId);
            throw new RuntimeException(errorMessage);
        }

        if (userGroupRepository.existsByGroupAndUser(group, user)) {
            String message = String.format("User with ID [%s] is already present in Group with ID [%s]", user.getId(), group.getId());
            throw new RuntimeException(message);
        }

        UserGroup userGroup = UserGroup.builder()
                .group(group)
                .user(user)
                .build();
        userGroupRepository.save(userGroup);
    }
}

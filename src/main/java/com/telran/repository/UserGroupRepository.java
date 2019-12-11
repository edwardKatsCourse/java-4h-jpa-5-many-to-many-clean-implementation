package com.telran.repository;

import com.telran.entity.Group;
import com.telran.entity.User;
import com.telran.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

    List<UserGroup> findAllByUser(User user);
    List<UserGroup> findAllByGroup(Group group);
    UserGroup findByUserAndGroup(User user, Group group);
    boolean existsByGroupAndUser(Group group, User user);
}

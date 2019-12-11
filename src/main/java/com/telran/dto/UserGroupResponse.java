package com.telran.dto;

import com.telran.entity.Group;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserGroupResponse {

    private Long userId;
    private String userName;
    List<Group> userGroups;
}

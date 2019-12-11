package com.telran.entity;

import lombok.*;

import javax.persistence.*;

@Entity
//user_group
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Group group;
}

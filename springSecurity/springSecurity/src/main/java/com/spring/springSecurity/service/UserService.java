package com.spring.springSecurity.service;

import com.spring.springSecurity.model.Role;
import com.spring.springSecurity.model.UserDetails;

import java.util.List;

public interface UserService {
    UserDetails saveUser(UserDetails user);

    Role saveRole(Role role);

    void addRoleToUser(String userName, String roleName);

    UserDetails getUser(String userName);

    List<UserDetails> getUsers();
}

package com.user.UserService.services;

import com.user.UserService.entities.User;

import java.util.List;

public interface UserService {
//    User createUser(User user);
    List<User> getUsers();
    User getUser(String userId);
    void deleteUser(String userId);
    User updateUser(User user,String userId);
}

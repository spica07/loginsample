package com.lgcns.login.service;

import java.util.List;

import com.lgcns.login.model.Users;

public interface UsersService {
	List<Users> getAllUsers();
	Users getUsersByUserId(String userId);
    boolean addUsers(Users users);
    void updateUsers(Users users);
    void deleteUsers(String userId);
    
}

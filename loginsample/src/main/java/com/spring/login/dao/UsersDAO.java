package com.spring.login.dao;

import java.util.List;

import com.spring.login.vo.Login;
import com.spring.login.vo.Users;

public interface UsersDAO {
    public List<Users> test() throws Exception;
    
    public Users validateUser(Login login) throws Exception;
}
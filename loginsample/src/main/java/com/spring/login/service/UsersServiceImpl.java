package com.spring.login.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.login.dao.UsersDAO;
import com.spring.login.vo.Login;
import com.spring.login.vo.Users;

@Service
public class UsersServiceImpl implements UsersService {
    @Inject
    private UsersDAO dao;

    @Override
    public List<Users> test() throws Exception {
        return dao.test();
    }
    
    @Override
    public Users validateUser(Login login)throws Exception{
        return dao.validateUser(login);
    }
}
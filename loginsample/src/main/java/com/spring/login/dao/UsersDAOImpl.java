package com.spring.login.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.login.vo.Login;
import com.spring.login.vo.Users;

@Repository
public class UsersDAOImpl implements UsersDAO {
    private static final String namespace="com.spring.login.testMapper";
    
    @Inject
    private SqlSession sqlSession;

    @Override
    public List<Users> test() throws Exception{
        return sqlSession.selectList(namespace+".test");
    }
    
    @Override
    public Users validateUser(Login login)throws Exception{
        return sqlSession.selectOne(namespace+".validateUser",login);
    }    
}

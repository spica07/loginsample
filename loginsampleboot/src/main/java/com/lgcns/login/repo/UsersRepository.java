package com.lgcns.login.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lgcns.login.model.Users;
 
 
public interface UsersRepository extends CrudRepository<Users, Long>{
	List<Users> findByUserId(String userId);
}

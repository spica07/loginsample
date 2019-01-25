package com.lgcns.login.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lgcns.login.model.Users;
import com.lgcns.login.repo.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepository usersRepository;
	
	@Override
	public List<Users> getAllUsers(){
		List<Users> list = new ArrayList<>();
		usersRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Users getUsersByUserId(String userId) {
		Users obj = usersRepository.findByUserId(userId).get(0);
		return obj;
	}

	@Override
	public boolean addUsers(Users users) {
		try {
		usersRepository.save(users);
		} catch(Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public void updateUsers(Users users) {
		Users obj = usersRepository.findByUserId(users.getUserId()).get(0);
		obj.setFirstName(users.getFirstName());
		obj.setLastName(users.getLastName());
		usersRepository.save(obj);
	}

	@Override
	public void deleteUsers(String userId) {
		Users obj = usersRepository.findByUserId(userId).get(0);		
		usersRepository.delete(obj);
	}
	
}

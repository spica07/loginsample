package com.lgcns.login.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lgcns.login.model.Users;
import com.lgcns.login.repo.UsersRepository;
 
 
@RestController
public class WebController {
	
	@Autowired
	UsersRepository repository;
	
	@GetMapping("/save")
	public String process(){
		repository.saveAll(Arrays.asList(  new Users("jin1@lgcns.com","Jack", "Smith"), 
										new Users("jin2@lgcns.com","Adam", "Johnson"),
										new Users("jin3@lgcns.com","Kim", "Smith"),
										new Users("jin4@lgcns.com","David", "Williams"),
										new Users("jin5@lgcns.com","Peter", "Davis")));
		
		return "Done";
	}

	@GetMapping("/findall")
	public String findAll(){
		
		String result = "";
		
		for(Users cust : repository.findAll()){
			result += cust + "</br>";
		}
		
		return result;
	}
	
	/*
	@GetMapping("/findbyid")
	public String findById(@RequestParam("id") long id){
		String result = "";
		result = repository.findById(id).toString();
		return result;
	}
	
	@GetMapping("/findbylastname")
	public String fetchDataByLastName(@RequestParam("lastname") String lastName){
		String result = "";
		
		for(Users cust: repository.findByLastName(lastName)){
			result += cust + "</br>"; 
		}
		
		return result;
	}
	*/
}

package com.lgcns.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.lgcns.login.model.Users;
import com.lgcns.login.service.UsersService;

@Controller
@RequestMapping("/login")
public class UsersController {
	@Autowired
	private UsersService userService;
	
	@GetMapping("users/{userId}")
	public ResponseEntity<Users> getUsersById(@PathVariable("userId") String userId) {
		Users users = userService.getUsersByUserId(userId);
		return new ResponseEntity<Users>(users, HttpStatus.OK);
	}
	@GetMapping("users")
	public ResponseEntity<List<Users>> getAllUsers() {
		List<Users> list = userService.getAllUsers();
		return new ResponseEntity<List<Users>>(list, HttpStatus.OK);
	}
	@PostMapping("users")
	public ResponseEntity<Void> addUsers(@RequestBody Users users, UriComponentsBuilder builder) {
        boolean flag = userService.addUsers(users);
        if (flag == false) {
	    return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/users/{userId}").buildAndExpand(users.getUserId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@PutMapping("users")
	public ResponseEntity<Users> updateUsers(@RequestBody Users users) {
		userService.updateUsers(users);
		return new ResponseEntity<Users>(users, HttpStatus.OK);
	}
	@DeleteMapping("users/{userId}")
	public ResponseEntity<Void> deleteUsers(@PathVariable("userId") String userId) {
		userService.deleteUsers(userId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
    @RequestMapping("/welcome")
    public String loginMessage(){
        return "welcome";
    }
}

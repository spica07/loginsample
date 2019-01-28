package com.lgcns.login.controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.codec.binary.Base32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.lgcns.login.model.Users;
import com.lgcns.login.service.UsersService;
import com.lgcns.login.util.OTP;

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
    
    @RequestMapping("/requestOtp")
    public ModelAndView requestOpt(Model model){
		// Allocating the buffer
	//	byte[] buffer = new byte[secretSize + numOfScratchCodes * scratchCodeSize];
		byte[] buffer = new byte[5 + 5 * 5];
		
		// Filling the buffer with random numbers.
		// Notice: you want to reuse the same random generator
		// while generating larger random number sequences.
		new Random().nextBytes(buffer);
	
		// Getting the key and converting it to Base32
		Base32 codec = new Base32();
	//	byte[] secretKey = Arrays.copyOf(buffer, secretSize);
		byte[] secretKey = Arrays.copyOf(buffer, 5);
		byte[] bEncodedKey = codec.encode(secretKey);
		
		// 생성된 Key!
		String encodedKey = new String(bEncodedKey);
		
		System.out.println("encodedKey : " + encodedKey);
		
	    //String url = getQRBarcodeURL(userName, hostName, secretKeyStr);
		// userName과 hostName은 변수로 받아서 넣어야 하지만, 여기선 테스트를 위해 하드코딩 해줬다.
		String url = OTP.getQRBarcodeURL("jk", "lgcns.com", encodedKey); // 생성된 바코드 주소!
		System.out.println("URL : " + url);
		
		ModelAndView mav = new ModelAndView("otpTest");
		mav.addObject("url", url);
		mav.addObject("encodedKey", encodedKey);
		return mav;
	}

    @RequestMapping("/otpTestResult")
    public ModelAndView otpTestResult(@RequestParam("user_code") String user_code, @RequestParam("encodedKey") String encodedKey, Model model){
		long l = new Date().getTime();
		long ll =  l / 30000;
		
		boolean check_code = false;
		try {
			// 키, 코드, 시간으로 일회용 비밀번호가 맞는지 일치 여부 확인. 
			check_code = OTP.check_code(encodedKey, Integer.parseInt(user_code), ll);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		ModelAndView mav = new ModelAndView("otpTest");
		
		// 일치한다면 true.
		System.out.println("check_code : " + check_code);
		return mav;
    }
}

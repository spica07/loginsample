package com.spring.login.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base32;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.login.service.UsersService;
import com.spring.login.util.OTP;
import com.spring.login.vo.Login;
import com.spring.login.vo.Users;

@Controller
public class UsersController {

    @Inject
    UsersService service;    

    @RequestMapping(value="/test",method = RequestMethod.GET)
    public String test(Model model) throws Exception {

        List<Users> list;        

        list = service.test();
        model.addAttribute("list",list); 

        return "test";
    }
    
    @RequestMapping(value="/")
    public String requestHandlingMethod(Model model, HttpServletRequest request) {
        return "home";
    }

    
    @RequestMapping(value="/requstgoogleOtp")
    public String requstgoogleOtp(Model model, HttpServletRequest request) {
        // Allocating the buffer
    //  byte[] buffer = new byte[secretSize + numOfScratchCodes * scratchCodeSize];
        byte[] buffer = new byte[5 + 5 * 5];
        
        // Filling the buffer with random numbers.
        // Notice: you want to reuse the same random generator
        // while generating larger random number sequences.
        new Random().nextBytes(buffer);
    
        // Getting the key and converting it to Base32
        Base32 codec = new Base32();
    //  byte[] secretKey = Arrays.copyOf(buffer, secretSize);
        byte[] secretKey = Arrays.copyOf(buffer, 5);
        byte[] bEncodedKey = codec.encode(secretKey);
        
        // 생성된 Key!
        String encodedKey = new String(bEncodedKey);
        
        System.out.println("encodedKey : " + encodedKey);
        
    //  String url = getQRBarcodeURL(userName, hostName, secretKeyStr);
        // userName과 hostName은 변수로 받아서 넣어야 하지만, 여기선 테스트를 위해 하드코딩 해줬다.
        String url = OTP.getQRBarcodeURL("jinklee", "lgcns.com", encodedKey); // 생성된 바코드 주소!
        System.out.println("URL : " + url);
        
        
        ModelAndView mav = new ModelAndView("Opt");
        mav.addObject("encodedKey", encodedKey);
        mav.addObject("url", url);
        
        return "otpTest.jsp";
    }
    
    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
    public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
        @ModelAttribute("login") Login login) throws Exception {
        ModelAndView mav = null;
        Users user = service.validateUser(login);
        if (null != user) {
        mav = new ModelAndView("welcome");
        mav.addObject("firstname", user.getUserId());
        } else {
        mav = new ModelAndView("login");
        mav.addObject("message", "Username or Password is wrong!!");
        }
        return mav;
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("login", new Login());
        return mav;
    } 
}
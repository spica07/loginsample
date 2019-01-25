package com.spring.login.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.login.service.UsersService;
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
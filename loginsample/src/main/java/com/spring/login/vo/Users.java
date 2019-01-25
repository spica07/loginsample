package com.spring.login.vo;

import java.util.Date;


public class Users {
    private String userId;
    private String password;        
    private String otpPassword;    
    private int    loginFCount;     
    private Date   loginLastDate;   
    private Date   passwordUpdate ;  
    
    public String getUserId(){
        return userId;
    }
    
    public void setUserId(String userId){
        this.userId = userId;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getOtpPassword(){
        return otpPassword;
    }
    
    public void setOtpPassword(String otpPassword){
        this.otpPassword = otpPassword;
    }
    
    public int getLoginFCount(){
        return loginFCount;
    }
    
    public void setLoginFCount(int loginFCount){
        this.loginFCount = loginFCount;
    }
    
    public Date getLoginLastDate(){
        return loginLastDate;
    }
    
    public void setLoginLastDate(Date loginLastDate){
        this.loginLastDate = loginLastDate;
    }
    
    public Date getPasswordUpdate(){
        return passwordUpdate;
    }
    
    public void setPasswordUpdate(Date passwordUpdate){
        this.passwordUpdate = passwordUpdate;
    }
}

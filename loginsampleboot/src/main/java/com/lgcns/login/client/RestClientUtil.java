package com.lgcns.login.client;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.lgcns.login.model.Users;

public class RestClientUtil {
	public void getUsersByIdDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/login/user/{userId}";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Users> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Users.class, 1);
        Users Users = responseEntity.getBody();
        System.out.println("UserId:"+Users.getUserId());
    }
    public void getAllUserssDemo() {
	HttpHeaders headers = new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/login/users";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Users[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Users[].class);
        Users[] Userss = responseEntity.getBody();
        for(Users Users : Userss) {
              System.out.println("UserId:"+Users.getUserId());
        }
    }
    public void addUsersDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/login/users";
        Users objUsers = new Users("jin8@lgcns.com","test2","test2");
        HttpEntity<Users> requestEntity = new HttpEntity<Users>(objUsers, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);
        System.out.println(uri.getPath()); 	
    }
    public void updateUsersDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/login/users";
        Users objUsers = new Users("jin6@lgcns.com","testu","testu");
	
        HttpEntity<Users> requestEntity = new HttpEntity<Users>(objUsers, headers);
        restTemplate.put(url, requestEntity);
    }
    public void deleteUsersDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/login/user/{userId}";
        HttpEntity<Users> requestEntity = new HttpEntity<Users>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, 4);        
    }
    public static void main(String args[]) {
    	RestClientUtil util = new RestClientUtil();
        //util.getUsersByIdDemo();
    	util.getAllUserssDemo();
    	util.addUsersDemo();
    	util.updateUsersDemo();
    	//util.deleteUsersDemo();
    }    

}

package com.example.a.a.dish_it;

public class User {

    public String id;
    public String userName;
    public String email;
    public String password;
    public String userType;

    public User(String id, String userName, String email, String password, String userType) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public User( ) {


    }

    public String getId(){
        return id;
    }

    public String setId(String id){
        return this.id =id;
    }

    public String getUserName(){
        return userName;
    }

    public String setUserName(String userName){
        return this.userName =userName;
    }

    public String getEmail(){
        return email;
    }

    public String setEmail(String email){
        return this.email =email;
    }

    public String getPassword(){
        return password;
    }

    public String setPassword(String password){
        return this.password =password;
    }

    public String getUserType(){
        return userType;
    }

    public String setUserType(String userType){
        return this.userType =userType;
    }
}

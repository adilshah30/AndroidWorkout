package com.adilshah.adil.androidworkout;

/**
 * Created by adil on 17/02/2018.
 */

public class User {

    int ID;
    String fullname;
    String username;
    String password;
    String email;
    String age;
    String profile_img;

    public void setID(int ID){
        this.ID = ID;
    }
    public int getID(){
        return this.ID;
    }

    public void setFullname(String fullname){
        this.fullname = fullname;
    }
    public String getFullname(){
        return this.fullname;
    }

    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return this.username;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }

    public void setEmail(String email){ this.email = email; }
    public String getEmail(){ return this.email; }

    public void setAge(String age){ this.age=age; }
    public String getAge(){ return this.age; }

    public void setProfile_img(String profile_img){ this.profile_img =  profile_img; }
    public String getProfile_img() {
        return this.profile_img;
    }

}

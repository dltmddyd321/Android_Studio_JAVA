package com.example.login_ex;

public class SignUp_Info {

    String name;
    String nickname;
    String birth;
    String phoneNumber;

    public SignUp_Info(String name, String nickname, String birth, String phoneNumber){
        this.name = name;
        this.nickname = nickname;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getNickname(){
        return this.nickname;
    }
    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    public String getBirth(){
        return this.birth;
    }
    public void setBirth(String birth){
        this.birth = birth;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

}

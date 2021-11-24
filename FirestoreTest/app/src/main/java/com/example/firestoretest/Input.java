package com.example.firestoretest;

public class Input {
    String userName;
    String img;
    String message;


    public Input(String userName, String img, String message) {
        this.userName = userName;
        this.img = img;
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
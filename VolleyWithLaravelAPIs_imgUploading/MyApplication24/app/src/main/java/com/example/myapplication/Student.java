package com.example.myapplication;

import android.graphics.Bitmap;

public class Student {

    String Id,Name,Phone,Email,Password,ImgUrl;

    public Student(String id, String name, String phone, String email, String password,String ImgUrl) {
        Id = id;
        Name = name;
        Phone = phone;
        Email = email;
        Password = password;
        this.ImgUrl="http://192.168.0.103:90/mylaravel/public/Storage/"+ImgUrl;

    }
}

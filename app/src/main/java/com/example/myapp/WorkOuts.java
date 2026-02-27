package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
@Entity
public class WorkOuts   {
    @Override
    public String toString() {
        return "User{" +
                "name=" + name +
                "email=" + email +
                ",password=" + password + '\'' +
                '}';
    }

    @ColumnInfo(name ="password",defaultValue = "dani")
    public String password;



    @PrimaryKey(autoGenerate = false)
    @NonNull
    public String name;
    @ColumnInfo(name = "email",defaultValue = "aa")
    public String email;


    public WorkOuts(){}

    public WorkOuts(String password, String name,String email){
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return this.name;
    }
}

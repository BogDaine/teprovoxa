package com.example.teprovoxa.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class UserEntity {

    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "username")
    public String username;

    @ColumnInfo(name = "password")
    public String password;


    public UserEntity(){}
    public UserEntity(String usr, String pwd){
        username = usr;
        password = pwd;
    }

    public void setUsername(String value){username = value;}
    public String getUsername(){return username;}

    public void setPassword(String value){password = value;}
    public String getPassword(){return password;}
}

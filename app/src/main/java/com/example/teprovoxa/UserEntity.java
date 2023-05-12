package com.example.teprovoxa;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    public int getUid;

    @ColumnInfo(name = "username")
    public String username;
    public void setUsername(String value){username = value;}
    public String getUsername(){return username;}

    @ColumnInfo(name = "password")
    public String password;
    public void setPassword(String value){password = value;}
    public String getPassword(){return password;}
}

package com.example.teprovoxa.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Comment {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name ="challenge_id")
    public int challengeId;
    @ColumnInfo(name ="username")
    public String username;
    @ColumnInfo(name ="text")
    public String text;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB, name = "image")
    public byte[] image;

    public Comment(){}
    public Comment(int cId, String usr, String txt, byte[] img){
        challengeId = cId;
        text = txt;
        username = usr;
        image = img;
    }
    public Comment(int cId, String usr, String txt){
        challengeId = cId;
        text = txt;
        username = usr;
    }
}

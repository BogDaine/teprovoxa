package com.example.teprovoxa;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class Challenge {
    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name = "id")
    int uid;

    @ColumnInfo(name = "username")
    public String username;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "text")
    public String text;

    Challenge(){}
    Challenge(String cTitle, String cText, String cUsername){
        title = cTitle;
        text = cText;
        username = cUsername;
    }
}

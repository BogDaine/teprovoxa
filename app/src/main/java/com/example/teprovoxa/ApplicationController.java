package com.example.teprovoxa;

import android.app.Application;

import androidx.room.Room;

import com.example.teprovoxa.data.AppDatabase;
import com.example.teprovoxa.data.Challenge;

public class ApplicationController extends Application {
    private static ApplicationController instance;
    private static AppDatabase appDatabase;

    private String loggedUser;
    private Challenge lastAccessedChallenge;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        setupDatabase();
    }

    private void setupDatabase(){
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app-database").build();
    }
    public void setLoggedUser(String usr){loggedUser = usr;}
    public String getLoggedUser(){return loggedUser;}

    public void setLastAccessedChallenge(Challenge challenge){
        lastAccessedChallenge = challenge;
    }
    public Challenge getLastAccessedChallenge(){
        return lastAccessedChallenge;
    }

    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }
    public static ApplicationController getInstance(){
        return instance;
    }
}

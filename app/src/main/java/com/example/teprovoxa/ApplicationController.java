package com.example.teprovoxa;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.room.Room;

public class ApplicationController extends Application {
    private static ApplicationController instance;
    private static AppDatabase appDatabase;

    private String loggedUser;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Log.d("AAAAA", "AAAAA");
        setupDatabase();
    }

    private void setupDatabase(){
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app-database").build();
        String backupDBPath = appDatabase.getOpenHelper().getWritableDatabase().getPath();
        Log.d("DBPATH", backupDBPath);
    }
    public void setLoggedUser(String usr){loggedUser = usr;}
    public String getLoggedUser(){return loggedUser;}

    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }
    public static ApplicationController getInstance(){
        return instance;
    }
}

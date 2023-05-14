package com.example.teprovoxa;

import android.app.Application;

import androidx.room.Room;

public class ApplicationController extends Application {
    private static ApplicationController instance;
    private static AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        setupDatabase();
    }

    private void setupDatabase(){
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app-database").build();
    }

    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }
    public static ApplicationController getInstance(){
        return instance;
    }
}

package com.example.teprovoxa;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.teprovoxa.UserEntity;

@Database(entities = {UserEntity.class, Challenge.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract ChallengeDao challengeDao();
}
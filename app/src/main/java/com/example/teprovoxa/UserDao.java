package com.example.teprovoxa;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.teprovoxa.UserEntity;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM UserEntity")
    List<UserEntity> getAll();

    //@Query("SELECT * FROM UserEntity WHERE uid IN (:userIds)")
    //List<UserEntity> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM UserEntity WHERE username = :username")
    public List<UserEntity> findByName(String username);

    @Insert
    public void insertAll(UserEntity... users);

    @Delete
    public void delete(UserEntity user);
}

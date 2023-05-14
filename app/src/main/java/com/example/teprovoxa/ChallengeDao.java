package com.example.teprovoxa;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ChallengeDao {
    @Query("SELECT * FROM challenge")
    public List<Challenge> findAll();

    @Insert
    public void insertAll(Challenge... challenges);

    @Delete
    public void delete(Challenge challenge);
}

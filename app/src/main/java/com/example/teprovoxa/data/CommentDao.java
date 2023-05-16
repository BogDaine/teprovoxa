package com.example.teprovoxa.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CommentDao {
    @Query("SELECT * FROM comment")
    List<Comment> getAll();

    @Query("SELECT * FROM comment WHERE challenge_id = :challengeId")
    public List<Comment> findByChallenge(int challengeId);

    @Insert
    public void insertAll(Comment... comments);

    @Delete
    public void delete(Comment user);
}

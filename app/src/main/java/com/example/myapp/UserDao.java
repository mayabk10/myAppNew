package com.example.myapp;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    LiveData<List<User>> getAll();

    @Query("SELECT * FROM user WHERE name=:name ")
    User getFromName(String name);
    @Insert
    void Insert(User user);

    @Delete
    void delete(User user);

    @Update
    void update(User user);
    @Query("DELETE FROM user")
    void deleteAll();
}

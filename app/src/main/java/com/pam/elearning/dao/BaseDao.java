package com.pam.elearning.dao;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

public interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(T entity);
}

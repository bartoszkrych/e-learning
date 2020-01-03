package com.pam.elearning.dao;

import androidx.room.Insert;

import java.util.List;

public interface BaseDao<T> {
    @Insert
    void insert(T entity);
}

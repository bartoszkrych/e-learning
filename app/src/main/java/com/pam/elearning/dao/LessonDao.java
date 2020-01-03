package com.pam.elearning.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.pam.elearning.model.Lesson;

@Dao
public interface LessonDao extends BaseDao<Lesson>{

    @Query("SELECT * FROM LESSON WHERE id = :id")
    LiveData<Lesson> findById(final Integer id);
}

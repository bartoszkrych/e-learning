package com.pam.elearning.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.pam.elearning.model.Lesson;

@Dao
public interface LessonDao {

    @Query("SELECT * FROM LESSON WHERE id = id")
    Lesson findById(final Integer id);
}

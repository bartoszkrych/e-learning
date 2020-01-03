package com.pam.elearning.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.pam.elearning.model.Question;

@Dao
public interface QuestionDao {

    @Query("SELECT * FROM QUESTION WHERE lesson_id = id")
    Question findByLessonId(final Integer id);
}
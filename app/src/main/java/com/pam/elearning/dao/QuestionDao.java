package com.pam.elearning.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.pam.elearning.model.Question;

@Dao
public interface QuestionDao extends BaseDao<Question> {

    @Query("SELECT * FROM QUESTION WHERE lesson_id = :id")
    LiveData<Question> findByLessonId(final Integer id);
}

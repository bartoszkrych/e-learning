package com.pam.elearning.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.pam.elearning.model.Answer;

import java.util.List;

@Dao
public interface AnswerDao extends BaseDao<Answer>{

    @Query("SELECT A.* FROM ANSWER A JOIN QUESTION Q ON A.question_id = Q.id  WHERE Q.lesson_id = :id")
    LiveData<List<Answer>> findByLessonId(final Integer id);

    @Query("UPDATE ANSWER SET isSelected = 1 WHERE id = :id ")
    void selectById(Integer id);

    @Query("UPDATE ANSWER SET isSelected = 0 WHERE id = :id ")
    void unSelectById(Integer id);
}

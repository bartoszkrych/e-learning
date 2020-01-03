package com.pam.elearning.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.pam.elearning.model.Answer;

import java.util.List;

@Dao
public interface AnswerDao extends BaseDao<Answer>{

    @Query("SELECT * FROM ANSWER WHERE question_id = :id")
    List<Answer> findByQuestionId(final Integer id);

    @Query("UPDATE ANSWER SET isSelected = 1 WHERE id = :id ")
    void selectById(Integer id);
}

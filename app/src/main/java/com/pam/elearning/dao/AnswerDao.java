package com.pam.elearning.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.pam.elearning.model.Answer;

import java.util.List;

@Dao
public interface AnswerDao extends BaseDao<Answer>{

    @Query("SELECT * FROM ANSWER WHERE question_id = :id")
    LiveData<List<Answer>> findByQuestionId(final Integer id);

    @Query("UPDATE ANSWER SET isSelected = 1 WHERE question_id = (SELECT id FROM QUESTION WHERE lesson_id = :id) AND contents = :contents")
    void selectByLessonIdAndContents(Integer id, String contents);

    @Query("UPDATE ANSWER SET isSelected = 0 WHERE id = :id ")
    void unSelectById(Integer id);
}

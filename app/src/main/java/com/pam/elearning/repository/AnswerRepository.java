package com.pam.elearning.repository;

import android.app.Application;

import com.pam.elearning.dao.AnswerDao;
import com.pam.elearning.database.ELearningDatabase;
import com.pam.elearning.model.Answer;

import java.util.List;

public class AnswerRepository {

    private AnswerDao answerDao;

    public AnswerRepository(Application application) {
        ELearningDatabase db = ELearningDatabase.getDatabase(application);
        this.answerDao = db.answerDao();
    }

    public List<Answer> getByQuestionId(Integer id) {
        return answerDao.findByQuestionId(id);
    }

    void selectById(Integer id) {
        ELearningDatabase.databaseWriteExecutor.execute(() -> answerDao.selectById(id));
    }
}

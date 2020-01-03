package com.pam.elearning.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.pam.elearning.dao.QuestionDao;
import com.pam.elearning.database.ELearningDatabase;
import com.pam.elearning.model.Question;

public class QuestionRepository {

    private QuestionDao questionDao;

    public QuestionRepository(Application application) {
        ELearningDatabase db = ELearningDatabase.getDatabase(application);
        this.questionDao = db.questionDao();
    }

    public LiveData<Question> getByLessonId(Integer id) {
        return questionDao.findByLessonId(id);
    }
}

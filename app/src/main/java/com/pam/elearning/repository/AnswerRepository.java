package com.pam.elearning.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

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

    public LiveData<List<Answer>> getByLessonId(Integer id) {
        return answerDao.findByLessonId(id);
    }

    public void selectByLessonIdAndContents(Integer id, String contents) {
        ELearningDatabase.databaseWriteExecutor.execute(() -> answerDao.selectByLessonIdAndContents(id, contents));
    }

    public void unSelectById(Integer id) {
        ELearningDatabase.databaseWriteExecutor.execute(() -> answerDao.unSelectById(id));
    }

    public void unselectAnswersByLessonId(Integer id) {
        ELearningDatabase.databaseWriteExecutor.execute(() -> answerDao.unselectAnswersByLessonId(id));
    }
}

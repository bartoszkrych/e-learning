package com.pam.elearning.repository;

import android.app.Application;

import com.pam.elearning.dao.LessonDao;
import com.pam.elearning.database.ELearningDatabase;
import com.pam.elearning.model.Lesson;

public class LessonRepository {

    private LessonDao lessonDao;

    public LessonRepository(Application application) {
        ELearningDatabase db = ELearningDatabase.getDatabase(application);
        this.lessonDao = db.lessonDao();
    }

    public Lesson getById(Integer id) {
        return lessonDao.findById(id);
    }
}

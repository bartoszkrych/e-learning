package com.pam.elearning.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.pam.elearning.dao.LessonDao;
import com.pam.elearning.database.ELearningDatabase;
import com.pam.elearning.model.Lesson;

public class LessonRepository {

    private LessonDao lessonDao;

    public LessonRepository(Application application) {
        ELearningDatabase db = ELearningDatabase.getDatabase(application);
        this.lessonDao = db.lessonDao();
    }

    public LiveData<Lesson> getById(Integer id) {
        return lessonDao.findById(id);
    }
}

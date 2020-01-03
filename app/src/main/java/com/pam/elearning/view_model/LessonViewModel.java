package com.pam.elearning.view_model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.pam.elearning.model.Lesson;
import com.pam.elearning.repository.LessonRepository;

public class LessonViewModel extends AndroidViewModel {

    private LessonRepository lessonRepository;

    public LessonViewModel(Application application)
    {
        super(application);
        lessonRepository = new LessonRepository(application);
    }

    public LiveData<Lesson> getById(Integer id) {
        return lessonRepository.getById(id);
    }
}

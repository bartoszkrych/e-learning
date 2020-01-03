package com.pam.elearning.view_model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.pam.elearning.model.Question;
import com.pam.elearning.repository.QuestionRepository;

public class QuestionViewModel extends AndroidViewModel {

    private QuestionRepository questionRepository;

    public QuestionViewModel(Application application)
    {
        super(application);
        questionRepository = new QuestionRepository(application);
    }

    public LiveData<Question> getByLessonId(Integer id) {
        return questionRepository.getByLessonId(id);
    }
}

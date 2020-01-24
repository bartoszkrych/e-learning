package com.pam.elearning.view_model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.pam.elearning.model.Answer;
import com.pam.elearning.repository.AnswerRepository;

import java.util.List;

public class AnswerViewModel extends AndroidViewModel {

    private AnswerRepository answerRepository;

    public AnswerViewModel(Application application) {
        super(application);
        answerRepository = new AnswerRepository(application);
    }

    public LiveData<List<Answer>> getByLessonId(Integer id) {
        return answerRepository.getByLessonId(id);
    }

    public void selectById(Integer id) {
        answerRepository.selectById(id);
    }

    public void unSelectById(Integer id) {
        answerRepository.unSelectById(id);
    }

}

package com.pam.elearning.view_model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.pam.elearning.model.Answer;
import com.pam.elearning.model.Question;
import com.pam.elearning.repository.AnswerRepository;
import com.pam.elearning.repository.QuestionRepository;

import java.util.List;

public class ResultViewModel extends AndroidViewModel {

    private AnswerRepository answerRepository;
    private QuestionRepository questionRepository;

    public ResultViewModel(Application application) {
        super(application);
        answerRepository = new AnswerRepository(application);
        questionRepository = new QuestionRepository(application);
    }

    public LiveData<Question> getQuestionByLessonId(Integer id) {
        return questionRepository.getByLessonId(id);
    }

    public LiveData<List<Answer>> getAnswersByLessonId(Integer id) {
        return answerRepository.getByLessonId(id);
    }

    public void unselectAnswersByLessonId(Integer id) {
        answerRepository.unselectAnswersByLessonId(id);
    }

}

package com.pam.elearning;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.pam.elearning.view_model.AnswerViewModel;
import com.pam.elearning.view_model.LessonViewModel;
import com.pam.elearning.view_model.QuestionViewModel;

public class MainActivity extends AppCompatActivity {

    private LessonViewModel lessonViewModel;
    private AnswerViewModel answerViewModel;
    private QuestionViewModel questionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lessonViewModel = new ViewModelProvider(this).get(LessonViewModel.class);
        answerViewModel = new ViewModelProvider(this).get(AnswerViewModel.class);
        questionViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);

        TextView title = findViewById(R.id.title);
        TextView text = findViewById(R.id.text);
        TextView que = findViewById(R.id.question);
        TextView ans = findViewById(R.id.answer);

//        Lesson lesson = lessonViewModel.getById(1).getValue();

        lessonViewModel.getById(1).observe(this, l -> {
                    if (l != null) {
                        title.setText(l.getTitle());
                        text.setText(l.getText());
                        questionViewModel.getByLessonId(l.getId()).observe(this, q -> {
                                    if (q != null) {
                                        que.setText(q.getContents());
                                        answerViewModel.getByQuestionId(4).observe(this, a -> {
                                            if (a != null && a.size() > 0) {
                                                ans.setText(a.get(0).getContents());
                                            }
                                        });
                                    }
                                }
                        );
                    }
                }
        );

//        Question question = questionViewModel.getByLessonId(lesson.getId()).getValue();
//
//        que.setText(question.getContents());
//
//        List<Answer> answers = answerViewModel.getByQuestionId(question.getId()).getValue();
//
//        ans.setText(answers.get(0).getContents());


    }

}

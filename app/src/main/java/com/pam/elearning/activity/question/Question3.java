package com.pam.elearning.activity.question;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.pam.elearning.R;
import com.pam.elearning.activity.Lesson1;
import com.pam.elearning.view_model.AnswerViewModel;
import com.pam.elearning.view_model.LessonViewModel;
import com.pam.elearning.view_model.QuestionViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Question3 extends AppCompatActivity {

    @BindView(R.id.question_3)
    TextView question;

    @BindView(R.id.radioButton_3_1)
    RadioButton radioButton1;

    @BindView(R.id.radioButton_3_2)
    RadioButton radioButton2;

    @BindView(R.id.radioButton_3_3)
    RadioButton radioButton3;

    @BindView(R.id.radioGroup3)
    RadioGroup radioGroup;

    final static Integer lesson_number = 3;

    private LessonViewModel lessonViewModel;
    private QuestionViewModel questionViewModel;
    private AnswerViewModel answerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_question3);
        setTitle("TEST");
        ButterKnife.bind(this);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.radioButton_3_1:
                    radioButton1.setSelected(true);
                    radioButton2.setSelected(false);
                    radioButton3.setSelected(false);
                    break;

                case R.id.radioButton_3_2:
                    radioButton1.setSelected(false);
                    radioButton2.setSelected(true);
                    radioButton3.setSelected(false);
                    break;
                case R.id.radioButton_3_3:
                    radioButton1.setSelected(false);
                    radioButton2.setSelected(false);
                    radioButton3.setSelected(true);
            }
        });

        lessonViewModel = new ViewModelProvider(this).get(LessonViewModel.class);
        questionViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);
        answerViewModel = new ViewModelProvider(this).get(AnswerViewModel.class);

        lessonViewModel.getById(lesson_number).observe(this, l -> {
                    if (l != null) {
                        questionViewModel.getByLessonId(lesson_number).observe(this, q -> {
                                    if (q != null) {
                                        question.setText(q.getContents());
                                        answerViewModel.getByQuestionId(q.getId()).observe(this, a -> {
                                            if (a != null && a.size() > 2) {
                                                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                                                    ((RadioButton) radioGroup.getChildAt(i)).setText(a.get(i).getContents());
                                                }
                                            }
                                        });
                                    }
                                }
                        );
                    }
                }
        );
    }


    public void endTest(View view) {
        lessonViewModel.getById(lesson_number).observe(this, l -> {
                    if (l != null) {
                        questionViewModel.getByLessonId(lesson_number).observe(this, q -> {
                                    if (q != null) {
                                        question.setText(q.getContents());
                                        answerViewModel.getByQuestionId(q.getId()).observe(this, a -> {
                                            if (a != null && a.size() > 2) {
                                                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                                                    if (radioGroup.getChildAt(i).isSelected()) {
                                                        answerViewModel.selectById(a.get(i).getId());
                                                        break;
                                                    }
                                                }
                                            }
                                        });
                                    }
                                }
                        );
                    }
                }
        );
        Intent i = new Intent(this, Lesson1.class);
        finish();
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}

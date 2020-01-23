package com.pam.elearning.activity.result;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.pam.elearning.R;
import com.pam.elearning.activity.MainActivity;
import com.pam.elearning.view_model.AnswerViewModel;
import com.pam.elearning.view_model.LessonViewModel;
import com.pam.elearning.view_model.QuestionViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Result3 extends AppCompatActivity {

    @BindView(R.id.result_3)
    TextView result;

    @BindView(R.id.answerGroup3)
    RadioGroup radioGroup;

    @BindView(R.id.imageView_3)
    ImageView answer1;
    @BindView(R.id.imageView2_3)
    ImageView answer2;
    @BindView(R.id.imageView3_3)
    ImageView answer3;


    final static Integer lesson_number = 3;

    private LessonViewModel lessonViewModel;
    private QuestionViewModel questionViewModel;
    private AnswerViewModel answerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_result3);
        setTitle("TEST - Result");
        ButterKnife.bind(this);

        lessonViewModel = new ViewModelProvider(this).get(LessonViewModel.class);
        questionViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);
        answerViewModel = new ViewModelProvider(this).get(AnswerViewModel.class);

        lessonViewModel.getById(lesson_number).observe(this, l -> {
                    if (l != null) {
                        questionViewModel.getByLessonId(lesson_number).observe(this, q -> {
                                    if (q != null) {
                                        result.setText(q.getContents());
                                        answerViewModel.getByQuestionId(q.getId()).observe(this, a -> {
                                            if (a != null && a.size() > 2) {
                                                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                                                    ((RadioButton) radioGroup.getChildAt(i)).setText(a.get(i).getContents());
                                                    if (a.get(i).getIsSelected()) {
                                                        ((RadioButton) radioGroup.getChildAt(i)).setChecked(true);
                                                        answerViewModel.unSelectById(a.get(i).getId());
                                                    }
                                                    if (a.get(i).getIsCorrect()) {
                                                        if (i != 0) answer1.setVisibility(View.INVISIBLE);
                                                        if (i != 1) answer2.setVisibility(View.INVISIBLE);
                                                        if (i != 2) answer3.setVisibility(View.INVISIBLE);
                                                    }
                                                    radioGroup.getChildAt(i).setEnabled(false);
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


    public void next(View view) {
        Intent i = new Intent(this, MainActivity.class);
        finish();
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}

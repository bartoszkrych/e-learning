package com.pam.elearning.activity.question;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pam.elearning.R;

import butterknife.BindView;

public class Question1 extends AppCompatActivity {

    @BindView(R.id.question_1)
    TextView question;

    @BindView(R.id.radioButton_1_1)
    RadioButton radioButton1;

    @BindView(R.id.radioButton_1_2)
    RadioButton radioButton2;

    @BindView(R.id.radioButton_1_3)
    RadioButton radioButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);


        setContentView(R.layout.activity_question1);
    }
}

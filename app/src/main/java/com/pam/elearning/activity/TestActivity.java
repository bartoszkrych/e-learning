package com.pam.elearning.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.pam.elearning.R;
import com.pam.elearning.fragment.QuestionFragment;
import com.pam.elearning.fragment.ResultFragment;

public class TestActivity extends AppCompatActivity {

    private int numberOfLesson = 1;
    private boolean isTest = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_test);
        setTitle("TEST");

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_test, new QuestionFragment(numberOfLesson++))
                .commit();
    }

    public void next(View view) {
        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        Fragment fragment;
        if (isTest && numberOfLesson <= 3) {
            fragment = new QuestionFragment(numberOfLesson++);
            if (numberOfLesson > 3) {
                numberOfLesson = 1;
                isTest = false;
            }
        } else if (numberOfLesson <= 3) {
            setTitle("TEST - Result");
            fragment = new ResultFragment(numberOfLesson++);
        } else {
            Intent test = new Intent(getApplicationContext(), MainActivity.class);
            finish();
            startActivity(test);
            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
            return;
        }
        ft.replace(R.id.container_test, fragment).commit();
    }
}

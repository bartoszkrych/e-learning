package com.pam.elearning.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.pam.elearning.R;
import com.pam.elearning.fragment.ResultFragment;

public class ResultActivity extends AppCompatActivity {

    private int numberOfLesson = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_result, new ResultFragment(numberOfLesson++))
                .commit();
    }

    public void next(View view) {
        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        if (numberOfLesson <= 3) {
            ft.replace(R.id.container_result, new ResultFragment(numberOfLesson++)).commit();
        } else {
            Intent test = new Intent(getApplicationContext(), MainActivity.class);
            finish();
            startActivity(test);
            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
        }
    }
}

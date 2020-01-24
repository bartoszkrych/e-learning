package com.pam.elearning.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pam.elearning.R;
import com.pam.elearning.activity.question.Question1;
import com.pam.elearning.fragment.Lesson1;
import com.pam.elearning.fragment.Lesson2;
import com.pam.elearning.fragment.Lesson3;
import com.pam.elearning.view_model.LessonViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String TAG_L1 = "Lesson1";
    private static final String TAG_L2 = "Lesson2";
    private static final String TAG_L3 = "Lesson3";
    private boolean isThird = false;


    LessonViewModel lessonViewModel;

    Fragment lessonFragment1;
    Fragment lessonFragment2;
    Fragment lessonFragment3;
    FragmentTransaction transaction;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        lessonViewModel = new ViewModelProvider(this).get(LessonViewModel.class);

        bottomNavigation.setOnNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            lessonFragment1 = new Lesson1();
            lessonFragment2 = new Lesson2();
            lessonFragment3 = new Lesson3();
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.container, lessonFragment1, TAG_L1);
            transaction.add(R.id.container, lessonFragment2, TAG_L2);
            transaction.add(R.id.container, lessonFragment3, TAG_L3);
            transaction.attach(lessonFragment1);
            transaction.detach(lessonFragment2);
            transaction.detach(lessonFragment3);
            transaction.commit();
        } else {
            lessonFragment1 = getSupportFragmentManager().findFragmentByTag(TAG_L1);

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        transaction = getSupportFragmentManager().beginTransaction();
        switch (menuItem.getItemId()) {
            case R.id.navigation_lesson_1:

                isThird = false;
                transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
                transaction.attach(lessonFragment1);
                transaction.detach(lessonFragment2);
                transaction.detach(lessonFragment3);
                transaction.addToBackStack(null);
                transaction.commit();
                return true;
            case R.id.navigation_lesson_2:
                if (isThird) {
                    isThird = false;
                    transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
                } else {
                    transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                }
                transaction.attach(lessonFragment2);
                transaction.detach(lessonFragment1);
                transaction.detach(lessonFragment3);
                transaction.addToBackStack(null);
                transaction.commit();
                return true;
            case R.id.navigation_lesson_3:
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                isThird = true;
                transaction.attach(lessonFragment3);
                transaction.detach(lessonFragment2);
                transaction.detach(lessonFragment1);

                transaction.addToBackStack(null);
                transaction.commit();
                return true;
            case R.id.navigation_test:
                transaction.detach(lessonFragment1);
                transaction.detach(lessonFragment2);
                transaction.detach(lessonFragment3);
                transaction.commit();
                Intent test = new Intent(getApplicationContext(), Question1.class);
                finish();
                startActivity(test);
                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                return true;
        }
        return false;
    }
}

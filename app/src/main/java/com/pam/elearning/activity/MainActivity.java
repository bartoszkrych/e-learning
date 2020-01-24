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
import com.pam.elearning.fragment.Lesson1;
import com.pam.elearning.fragment.Lesson2;
import com.pam.elearning.fragment.Lesson3;
import com.pam.elearning.view_model.LessonViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private boolean fromRight = false;

    LessonViewModel lessonViewModel;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        lessonViewModel = new ViewModelProvider(this).get(LessonViewModel.class);

        bottomNavigation.setOnNavigationItemSelectedListener(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new Lesson1())
                .commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_lesson_1:
                fromRight = true;
                return switchFragment(new Lesson1());
            case R.id.navigation_lesson_2:
                return switchFragment(new Lesson2());
            case R.id.navigation_lesson_3:
                switchFragment(new Lesson3());
                fromRight = true;
                return true;
            case R.id.navigation_test:
                Intent test = new Intent(getApplicationContext(), TestActivity.class);
                finish();
                startActivity(test);
                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                return true;
        }
        return false;
    }

    private boolean switchFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        if (fromRight) {
            fromRight = false;
            ft.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
        } else {
            ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        }
        ft.replace(R.id.container, fragment).commit();

        return true;
    }
}

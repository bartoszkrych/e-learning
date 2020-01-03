package com.pam.elearning;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.pam.elearning.view_model.LessonViewModel;

public class MainActivity extends AppCompatActivity {

    private LessonViewModel lessonViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lessonViewModel = new ViewModelProvider(this).get(LessonViewModel.class);


        TextView textView = findViewById(R.id.first);

        lessonViewModel.getById(1).observe(this, lesson -> textView.setText(lesson.getTitle()));


    }

}

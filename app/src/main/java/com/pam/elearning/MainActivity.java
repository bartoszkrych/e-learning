package com.pam.elearning;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pam.elearning.model.Lesson;
import com.pam.elearning.view_model.LessonViewModel;

public class MainActivity extends AppCompatActivity {

    private LessonViewModel lessonViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Lesson lesson = lessonViewModel.getById(1);

        TextView textView = findViewById(R.id.first);

        textView.setText(lesson.getTitle());
    }
}

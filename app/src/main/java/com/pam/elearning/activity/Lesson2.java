package com.pam.elearning.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.pam.elearning.R;
import com.pam.elearning.view_model.LessonViewModel;
import com.potyvideo.library.AndExoPlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Lesson2 extends AppCompatActivity {

    private LessonViewModel lessonViewModel;

    @BindView(R.id.tit2)
    TextView title;

    @BindView(R.id.txt2)
    TextView text;

    @BindView(R.id.andExoPlayerView2)
    AndExoPlayerView media;

    private static int lesson_number = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson2);
        ButterKnife.bind(this);

        text.setMovementMethod(new ScrollingMovementMethod());

        lessonViewModel = new ViewModelProvider(this).get(LessonViewModel.class);

        lessonViewModel.getById(lesson_number).observe(this, l ->
                {
                    if (l != null) {
                        title.setText(l.getTitle());
                        text.setText(l.getText());
                        media.setSource(l.getUrl());
                    }
                }
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.lessons_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_lesson_1:
                Intent i = new Intent(this, Lesson1.class);
                finish();
                startActivity(i);
                return true;
            case R.id.item_lesson_2:
                Toast.makeText(this, "This is it!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item_lesson_3:
                Intent i2 = new Intent(this, Lesson3.class);
                finish();
                startActivity(i2);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

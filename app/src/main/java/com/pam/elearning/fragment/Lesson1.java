package com.pam.elearning.fragment;


import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.pam.elearning.R;
import com.pam.elearning.view_model.LessonViewModel;
import com.potyvideo.library.AndExoPlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Lesson1 extends Fragment {

    private LessonViewModel lessonViewModel;

    @BindView(R.id.tit1)
    TextView title;

    @BindView(R.id.txt1)
    TextView text;

    @BindView(R.id.andExoPlayerView1)
    AndExoPlayerView media;

    private static int lesson_number = 1;

    public Lesson1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lesson1, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        text.setMovementMethod(new ScrollingMovementMethod());

        lessonViewModel = new ViewModelProvider(requireActivity()).get(LessonViewModel.class);

        lessonViewModel.getById(lesson_number).observe(getViewLifecycleOwner(), l ->
                {
                    if (l != null) {
                        title.setText(l.getTitle());
                        text.setText(l.getText());
                        media.setSource(l.getUrl());
                    }
                }
        );
    }
}

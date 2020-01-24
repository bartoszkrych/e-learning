package com.pam.elearning.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.pam.elearning.R;
import com.pam.elearning.view_model.AnswerViewModel;
import com.pam.elearning.view_model.LessonViewModel;
import com.pam.elearning.view_model.QuestionViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {

    @BindView(R.id.result_question)
    TextView result;

    @BindView(R.id.answer_group)
    RadioGroup radioGroup;

    @BindView(R.id.tick_1)
    ImageView answer1;
    @BindView(R.id.tick_2)
    ImageView answer2;
    @BindView(R.id.tick_3)
    ImageView answer3;

    private int lessonNumber;

    private LessonViewModel lessonViewModel;
    private QuestionViewModel questionViewModel;
    private AnswerViewModel answerViewModel;

    public ResultFragment() {
        // Required empty public constructor
    }

    public ResultFragment(int lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lessonViewModel = new ViewModelProvider(requireActivity()).get(LessonViewModel.class);
        questionViewModel = new ViewModelProvider(requireActivity()).get(QuestionViewModel.class);
        answerViewModel = new ViewModelProvider(requireActivity()).get(AnswerViewModel.class);

        questionViewModel.getByLessonId(lessonNumber).observe(getViewLifecycleOwner(), q -> {
            if (q != null) {
                result.setText(q.getContents());
                answerViewModel.getByQuestionId(q.getId()).observe(getViewLifecycleOwner(), a -> {
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

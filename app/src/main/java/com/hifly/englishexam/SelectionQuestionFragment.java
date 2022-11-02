package com.hifly.englishexam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hifly.englishexam.databinding.FragmentQuestionBinding;

public class SelectionQuestionFragment extends Fragment {
    private final Question question;

    public SelectionQuestionFragment(Question question) {
        this.question = question;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentQuestionBinding fragmentQuestionBinding = FragmentQuestionBinding.inflate(inflater);
        fragmentQuestionBinding.title.setText(question.getTitle());

        if (question.getSelection1().isEmpty()) {
            fragmentQuestionBinding.selection1Layout.setVisibility(View.GONE);
        } else {
            fragmentQuestionBinding.selection1Layout.setVisibility(View.VISIBLE);
            fragmentQuestionBinding.selection1.setText("A. " + question.getSelection1());
        }

        if (question.getSelection2().isEmpty()) {
            fragmentQuestionBinding.selection2Layout.setVisibility(View.GONE);
        } else {
            fragmentQuestionBinding.selection2Layout.setVisibility(View.VISIBLE);
            fragmentQuestionBinding.selection2.setText("A. " + question.getSelection2());
        }

        if (question.getSelection3().isEmpty()) {
            fragmentQuestionBinding.selection3Layout.setVisibility(View.GONE);
        } else {
            fragmentQuestionBinding.selection3Layout.setVisibility(View.VISIBLE);
            fragmentQuestionBinding.selection3.setText("A. " + question.getSelection3());
        }

        if (question.getSelection4().isEmpty()) {
            fragmentQuestionBinding.selection4Layout.setVisibility(View.GONE);
        } else {
            fragmentQuestionBinding.selection4Layout.setVisibility(View.VISIBLE);
            fragmentQuestionBinding.selection4.setText("A. " + question.getSelection4());
        }
        fragmentQuestionBinding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String anwser = "";
                if (fragmentQuestionBinding.radioGroup.getCheckedRadioButtonId() == fragmentQuestionBinding.radio1.getId()) {
                    anwser = "A";
                } else if (fragmentQuestionBinding.radioGroup.getCheckedRadioButtonId() == fragmentQuestionBinding.radio2.getId()) {
                    anwser = "B";
                } else if (fragmentQuestionBinding.radioGroup.getCheckedRadioButtonId() == fragmentQuestionBinding.radio3.getId()) {
                    anwser = "C";
                } else if (fragmentQuestionBinding.radioGroup.getCheckedRadioButtonId() == fragmentQuestionBinding.radio4.getId()) {
                    anwser = "D";
                }
                if (anwser.equals(question.getAnswer())) {
                    Toast.makeText(getActivity(), "您答对了", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "您答错了", Toast.LENGTH_LONG).show();
                }
            }
        });
        return fragmentQuestionBinding.getRoot();
    }
}

package com.hifly.englishexam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hifly.englishexam.databinding.FragmentWordPuzzleBinding;

public class WordPuzzleFragment extends Fragment {
    private final Question question;

    public WordPuzzleFragment(Question question) {
        this.question = question;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentWordPuzzleBinding fragmentWordPuzzleBinding = FragmentWordPuzzleBinding.inflate(inflater);
        fragmentWordPuzzleBinding.title.setText(question.getTitle());
        fragmentWordPuzzleBinding.answer.setText(question.getAnswer());
        return fragmentWordPuzzleBinding.getRoot();
    }
}

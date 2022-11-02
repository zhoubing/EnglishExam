package com.hifly.englishexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.hifly.englishexam.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final ArrayList<Fragment> fragments = new ArrayList<>(10);
    private final ArrayList<Question> questions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        initQuestions();
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getType() == 0) {
                SelectionQuestionFragment selectionQuestionFragment = new SelectionQuestionFragment(questions.get(i));
                fragments.add(selectionQuestionFragment);
            } else if (questions.get(i).getType() == 1) {
                WordPuzzleFragment wordPuzzleFragment = new WordPuzzleFragment(questions.get(i));
                fragments.add(wordPuzzleFragment);
            }
        }
        activityMainBinding.viewpage.setOffscreenPageLimit(10);
        activityMainBinding.viewpage.setUserInputEnabled(false);
        activityMainBinding.viewpage.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return fragments.get(position);
            }

            @Override
            public int getItemCount() {
                return fragments.size();
            }
        });
        activityMainBinding.viewpage.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {

            }
        });
    }

    private void initQuestions() {
        Question question = new Question();
        question.setType(0);
        question.setTitle("Come here, Mary, look, the computer game is______.");
        question.setSelection1("in");
        question.setSelection2("Play");
        question.setSelection3("on");
        question.setAnswer("A");
        questions.add(question);

        question = new Question();
        question.setType(1);
        question.setTitle("theirs(宾格)");
        question.setAnswer("them");
        questions.add(question);
    }
}
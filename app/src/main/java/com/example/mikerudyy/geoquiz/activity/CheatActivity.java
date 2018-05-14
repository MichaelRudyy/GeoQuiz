package com.example.mikerudyy.geoquiz.activity;

import com.example.mikerudyy.geoquiz.R;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import javax.xml.transform.Result;

public class CheatActivity extends AppCompatActivity {

    private Button showButton;
    private TextView textView;
    private static final String EXTRA_ANSWER = "com.example.mikerudyy.geoquiz.activity.answer";
    private static final String EXTRA_SHOW_ANSWER = "com.example.mikerudyy.geoquiz.activity.answer_shown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpActivity(savedInstanceState);
        buttonActions();
    }

    protected void setUpActivity(Bundle savedinstanceState) {
        setContentView(R.layout.activity_cheat);
        showButton = findViewById(R.id.show_button);
        textView = findViewById(R.id.cheat_text_view);

    }

    public static Intent newIntent(Context pageContext, boolean answer) {
        Intent intent = new Intent(pageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER, answer);
        return intent;
    }

    private void showAnswer() {
        boolean answer = getIntent().getBooleanExtra(EXTRA_ANSWER, false);
        textView.setText(String.valueOf(answer));
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_SHOW_ANSWER, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    public static boolean isAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_SHOW_ANSWER, false);
    }

    private void buttonActions() {
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showButton.setEnabled(false);
                showAnswer();
                setAnswerShownResult(true);
            }
        });
    }
}

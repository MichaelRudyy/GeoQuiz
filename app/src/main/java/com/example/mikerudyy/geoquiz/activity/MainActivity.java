package com.example.mikerudyy.geoquiz.activity;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mikerudyy.geoquiz.R;
import com.example.mikerudyy.geoquiz.model.Question;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionTextView;

    private Resources resources;

    @SuppressLint("ResourceType")
    private Question[] questions;

    private int currentIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate() - set-up's values");
        setUpActivity();
        buttonActions();
        Log.d(TAG,"onCreate() - send of et-up's values");
    }

    protected void setUpActivity(){
        setContentView(R.layout.activity_main);

        resources = getResources();

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.question_text_view);

        questions = new Question[]{
                new Question(R.string.question_africa,
                        resources.getBoolean(R.bool.answer_africa)),
                new Question(R.string.question_americas,
                        resources.getBoolean(R.bool.answer_america)),
                new Question(R.string.question_asia,
                        resources.getBoolean(R.bool.answer_asia)),
                new Question(R.string.question_australia,
                        resources.getBoolean(R.bool.answer_australia)),
                new Question(R.string.question_mideast,
                        resources.getBoolean(R.bool.answer_mideast)),
                new Question(R.string.question_oceans,
                        resources.getBoolean(R.bool.answer_oceans))
        };

        questionTextView.setText(questions[0].getTextResId());
    }

    protected void checkAnswer(boolean userAnswer) {
        boolean answer = questions[currentIndex].getAnswer();
        int messageID;

        if (userAnswer == answer) {
            messageID = R.string.correct_toast;
        } else {
            messageID = R.string.incorrect_toast;
        }

        showToastWithTextId(messageID);
    }

    protected void updateQuestion() {
        currentIndex = (currentIndex + 1) % questions.length;
        int questionId = questions[currentIndex].getTextResId();
        questionTextView.setText(questionId);
    }

    protected void showToastWithTextId(int messageId) {
        Toast toast = Toast.makeText(MainActivity.this, messageId, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }

    protected void buttonActions() {
        trueButton.setOnClickListener(v -> {
            checkAnswer(true);
        });

        falseButton.setOnClickListener(v -> {
            checkAnswer(false);
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuestion();
            }
        });
    }

    // Voids just to look how activity life cycle works

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume()");
    }
}

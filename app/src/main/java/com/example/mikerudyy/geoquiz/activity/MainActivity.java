package com.example.mikerudyy.geoquiz.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mikerudyy.geoquiz.R;
import com.example.mikerudyy.geoquiz.model.Question;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT = 0;

    private Button trueButton;
    private Button falseButton;
    private ImageButton nextButton;
    private Button cheatButton;
    private TextView questionTextView;
    private TextView cheaterStutsTextView;

    private Resources resources;

    private boolean userIsCheater;

    @SuppressLint("ResourceType")
    private Question[] questions;

    private int currentIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() - set values / Start");
        setUpActivity(savedInstanceState);
        buttonActions();
        Log.d(TAG, "onCreate() - set values / End");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "Device was rotated");
        outState.putInt(KEY_INDEX, currentIndex);
    }

    protected void setUpActivity(Bundle savedinstanceState) {
        setContentView(R.layout.activity_main);

        resources = getResources();

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        cheatButton = findViewById(R.id.cheat_button);
        cheaterStutsTextView = findViewById(R.id.cheater_status_text_view);

        cheaterStutsTextView.setText("Version of SDK is : " + Build.VERSION.SDK);

        //cheaterStutsTextView.setText(String.valueOf(userIsCheater));

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

        if (savedinstanceState != null) {
            questionTextView.setText(
                    questions[savedinstanceState.getInt(KEY_INDEX)]
                            .getTextResId()
            );
        } else {
            questionTextView.setText(
                    questions[0].getTextResId())
            ;
        }


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

        cheatButton.setOnClickListener((v -> {
            Intent intent = CheatActivity
                    .newIntent(
                            MainActivity.this,
                            questions[currentIndex].getAnswer()
                    );
            startActivityForResult(intent,REQUEST_CODE_CHEAT);
        }));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK){
            return;
        }else if (requestCode == REQUEST_CODE_CHEAT){
            if (data==null){
                return;
            }else{
                userIsCheater = CheatActivity.isAnswerShown(data);
                cheaterStutsTextView.setText(String.valueOf(userIsCheater));
            }
        }
    }
}

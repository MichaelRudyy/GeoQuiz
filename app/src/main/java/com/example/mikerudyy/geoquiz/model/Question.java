package com.example.mikerudyy.geoquiz.model;

/**
 * Created by mikeRudyy on 27-Mar-18.
 */

public class Question {
    private int textResId;
    private boolean answer;

    public Question(int textResId, boolean answer) {
        this.textResId = textResId;
        this.answer = answer;
    }

    public int getTextResId() {
        return textResId;
    }

    public void setTextResId(int textResId) {
        this.textResId = textResId;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}

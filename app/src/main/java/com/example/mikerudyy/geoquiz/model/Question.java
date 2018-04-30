package com.example.mikerudyy.geoquiz.model;

import android.content.res.Resources;

import com.example.mikerudyy.geoquiz.R;

/**
 * Думаю что по факту правильней было бы создавать questions с прееменными ID что по вопросу,
 * что по ответу
 *
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

    public boolean getAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}

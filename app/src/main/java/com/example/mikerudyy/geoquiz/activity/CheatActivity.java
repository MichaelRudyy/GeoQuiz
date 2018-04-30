package com.example.mikerudyy.geoquiz.activity;

import com.example.mikerudyy.geoquiz.R;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class CheatActivity extends AppCompatActivity {

    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        backButton = findViewById(R.id.back_button);
        System.out.println(backButton);
        buttonActions();
    }

    private void buttonActions(){
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CheatActivity.this,MainActivity.class));
            }
        });
    }
}

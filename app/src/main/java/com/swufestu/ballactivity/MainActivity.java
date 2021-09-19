package com.swufestu.ballactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    int score1 = 0;
    int score2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View btn) {
        Log.i(TAG, "click: ");
        if(btn.getId()==R.id.btn3){
            score1 += 3;
        }else if(btn.getId()==R.id.btn2){
            score1 += 2;
        }else if(btn.getId()==R.id.btn1){
            score1 ++;
        }else{
            //reset
            score1 = 0;
            score2 = 0;
        }

        //显示分数到控件中
        TextView out = findViewById(R.id.score1);
        out.setText(String.valueOf(score1));
    }

    public void clickb(View btn) {
        Log.i(TAG, "click: ");
        if(btn.getId()==R.id.btnb3){
            score2 += 3;
        }else if(btn.getId()==R.id.btnb2){
            score2 += 2;
        }else if(btn.getId()==R.id.btnb1){
            score2 ++;
        }else{
            //reset
            score2 = 0;
        }

        //显示分数到控件中
        TextView out = findViewById(R.id.score2);
        out.setText(String.valueOf(score2));
    }
}
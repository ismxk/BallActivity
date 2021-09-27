package com.swufestu.ballactivity;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;

public class FirstActivity extends AppCompatActivity implements Runnable{

    private static final String TAG = "FirstActivity";
    private float dollar_rate=0.35f;
    private float euro_rate=0.28f;
    private float won_rate=501f;
    TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        TextView show = findViewById(R.id.show);
        //读取保存的数据
        SharedPreferences sp = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        dollar_rate = sp.getFloat("dollor_rate",0.1f);
        euro_rate = sp.getFloat("euro_rate",0.2f);
        won_rate = sp.getFloat("won_rate",0.3f);
        Log.i(TAG, "onCreate: get from sp dollar="+dollar_rate);
        Log.i(TAG, "onCreate: get from sp euro="+euro_rate);
        Log.i(TAG, "onCreate: get from sp won="+won_rate);


        //保存数据到sp
        SharedPreferences sp = getSharedPreferences();
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat("dollor_rate",dollar_rate);
        editor.putFloat("euro_rate",euro_rate);
        editor.putFloat("euro_rate",won_rate);
        editor.apply();
        Log.i(TAG, "onActivityResult:save to sp ");
    }
    super.onActivityResult(requestCode,resultCode,data);

    private SharedPreferences getSharedPreferences() {
    }


    @Override
    public void onClick(View btn) {
        Log.i(TAG, "onClick: ");
       // EditText rmb= findViewById(R.id.input_rmb);
        TextView result=findViewById(R.id.result);
        BreakIterator rmb;
        String inp=rmb.getText().toString();cccccccccccccccccccccccccccccccc
        if(inp.length()>0){
            float num=Float.parseFloat(inp);
            float r=0;
            if(btn.getId()==R.id.btn_dollar){
                r=num*dollar_rate;
            }else if(btn.getId()==R.id.btn_euro){
                r=num*euro_rate;
            }else if(btn.getId()==R.id.btn_won){
                r=num*won_rate;
            }
            result.setText(String.valueOf(r));
        }else{
            Toast.makeText(this,"请输入金额后再进行转换",Toast.LENGTH_SHORT).show();
        }
    }

    //调转工程自身页面,数据传递
    public void open(View btn){
        Log.i(TAG, "open: ");
        Intent config=new Intent(this,ConfigActivity.class);
        config.putExtra("dollar_rate_key",dollar_rate);
        config.putExtra("euro_rate_key",euro_rate);
        Intent won_rate_key = config.putExtra("won_rate_key", won_rate);
        startActivityForResult(config,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //区分哪个窗口带回的数据
        //区分多个窗口带回数据
        if(requestCode==1&&resultCode==3){
            dollar_rate=data.getFloatExtra("dollar_key2",0.1f);
            euro_rate=data.getFloatExtra("euro_key2",0.1f);
            won_rate=data.getFloatExtra("won_key2",0.1f);
        }else if(requestCode==1&&resultCode==5){
            Bundle bdl=data.getExtras();
            dollar_rate=data.getFloatExtra("dollar_key3",1);
            euro_rate=data.getFloatExtra("euro_key3",1);
            won_rate=data.getFloatExtra("won_key3",1);
            Log.i(TAG,"onActivityResult: dollar_rate"+dollar_rate);
            Log.i(TAG,"onActivityResult: euro_rate"+euro_rate);
            Log.i(TAG,"onActivityResult: won_rate"+won_rate);

            handler = new handler();

            //开启线程
            Thread t = new Thread(this);
            t.start();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public void run() {
        Log.i(TAG, "run: ...");
        //发送消息
        Massage msg = handler.obtainMessage();

    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if(item.getItemId()==R.id.setting){
//
//        }
//    }
}


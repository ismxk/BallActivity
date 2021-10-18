package com.swufestu.ballactivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.swufestu.ballactivity.R;

import java.util.ArrayList;
import java.util.HashMap;

public class RateList2Activity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "RateList2Activity";
    ListView mylist2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_list2);
        mylist2=findViewById(R.id.mylist2);
        mylist2.setOnItemClickListener(this);

        ProgressBar bar=findViewById(R.id.progressBar);

        //处理返回数据
        Handler handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(Message msg){
                super.handleMessage(msg);
                if(msg.what==9){
                    ArrayList<HashMap<String,String>> rlist = (ArrayList<HashMap<String,String>>) msg.obj;
                    SimpleAdapter listItemAdapter=new SimpleAdapter(RateList2Activity.this,
                            rlist,R.layout.list_item,
                            new String[]{"ItemTitle","ItemDetail"},
                            new int[]{R.id.itemTitle,R.id.itemDetail});
                    mylist2.setAdapter(listItemAdapter);
                    bar.setVisibility(View.GONE);
                    mylist2.setVisibility(View.VISIBLE);
                }
            }
        };
        com.swufe.hello.RateMapThread first=new com.swufe.hello.RateMapThread();
        first.setHandler(handler);
        Thread t2 = new Thread(first);
        t2.start();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        //获取参数
        Object itemAtPosition= mylist2.getItemAtPosition(position);
        HashMap<String,String> map=(HashMap<String,String>) itemAtPosition;
        String titleStr=map.get("ItemTitle");
        String detailStr=map.get("ItemDetail");
        Log.i(TAG,"onItemClick: titleStr="+titleStr);
        Log.i(TAG,"onItemClick: detailStr="+detailStr);

        Intent config = new Intent(this, com.swufe.hello.Ratelist3Activity.class);
        config.putExtra("ttitle", titleStr);
        config.putExtra("ddetail", detailStr);
        //跳转新的页面
        startActivityForResult(config, 1);
    }
}
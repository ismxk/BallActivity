package com.swufestu.ballactivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.swufestu.ballactivity.R;

import java.util.ArrayList;
public class RateListActivity extends AppCompatActivity {
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_list);
        ListView listView=findViewById(R.id.mylist);

        String[] list_data={"one","two","three","four"};
        ListAdapter adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list_data);
//        listView.setListAdapter(adapter);


        handler=new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==7){
                    ArrayList<String> rlist=(ArrayList<String>)msg.obj;
                    ListAdapter adapter=new ArrayAdapter<String>(RateListActivity.this, android.R.layout.simple_list_item_1,rlist);
                    listView.setAdapter(adapter);
                }

                super.handleMessage(msg);
            }
        };



        com.swufe.hello.RateThread dt=new com.swufe.hello.RateThread(handler);
        Thread t=new Thread(dt);
        t.start();
    }
}

package com.swufestu.ballactivity;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RateMapThread implements Runnable{
    private static final String TAG = "RateMapThread";
    private Handler handler;

    public void setHandler(Handler handler){
        this.handler=handler;
    }

    //解析网页汇率
    public void run() {
        List<HashMap<String,String>> retlist = new ArrayList<HashMap<String,String>>();
        //解析网页汇率并获取
        try {
            Document doc= Jsoup.connect(" https://www.boc.cn/sourcedb/whpj").get();//获取网页
            Log.i(TAG, "run: title=" + doc.title());
            Element firstTable=doc.getElementsByTag("tbody").get(1);;
            Elements trs=firstTable.getElementsByTag("tr");
            trs.remove(0);
            for(Element tr: trs){//获取网页中的汇率
                Log.i(TAG, "run: r=" + tr);
                Elements tds=tr.getElementsByTag("td");
                String td1=tds.get(0).text();
                String td2=tds.get(5).text();
                Log.i(TAG, "run: td1=" + td1+"\t td2=" + td2);

                HashMap<String,String> map=new HashMap<String,String>();
                map.put("ItemTitle",td1);
                map.put("ItemDetail",td2);
                retlist.add(map);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //发送消息
        Message msg = handler.obtainMessage(9,retlist);
        handler.sendMessage(msg);
    }
}

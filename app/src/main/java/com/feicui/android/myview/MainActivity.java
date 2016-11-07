package com.feicui.android.myview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class MainActivity extends Activity {
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // MyCustum myCustum = new MyCustum(this);
        setContentView(R.layout.myview);
//        setContentView(new MyView(this));
//        handler= new Handler(){
//            @Override
//            public void handleMessage(Message msg) {
//                if (msg.what == 2){
//                    Log.i("yzg", "222222222222222");
////                    handler.sendEmptyMessage(2);
//                }
//            }
//        };
////       setContentView(R.layout.myview);
//        initView();
    }

    public void initView(){
//        Log.i("yzg", "222222");
        new Thread(new Runnable() {
            @Override
            public void run() {
//                Log.i("yzg", "222222");
                Message message = new Message();
                message.what = 2;
                handler.sendMessage(message);
            }
        }).start();

    }
}

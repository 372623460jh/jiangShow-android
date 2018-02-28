package com.jianghe.jiangshow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_astest);

        new Thread() {
            public void run() {
                try {
                    Thread.sleep(2000);
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, WebViewActivity.class);
                    //利用bundle来存取数据
                    Bundle bundle = new Bundle();
                    //再把bundle中的数据传给intent，以传输过去
                    intent.putExtras(bundle);
                    startActivityForResult(intent, 0);
                    MainActivity.this.overridePendingTransition(0, 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}

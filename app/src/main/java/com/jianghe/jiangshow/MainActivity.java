package com.jianghe.jiangshow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn1 = null;// 按钮1
    Button btn2 = null;// 按钮2
    Context cn = null;// 上下文对象

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_astest);
        cn = this;
        this.init();
    }

    //初始化方法
    private void init() {
        //获取按钮
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);

        btn1.setOnClickListener(new MyClickListener());//添加监听
        btn2.setOnClickListener(new MyClickListener());//添加监听
    }

    //事件处理内部类
    class MyClickListener implements View.OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn1:
                    Toast.makeText(cn, "点击了按钮1", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn2:
                    Toast.makeText(cn, "点击了按钮2", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, WebViewActivity.class);
                    //利用bundle来存取数据
                    Bundle bundle = new Bundle();
                    //再把bundle中的数据传给intent，以传输过去
                    intent.putExtras(bundle);
                    startActivityForResult(intent, 0);
                    break;
                default:
                    break;
            }
        }
    }
}

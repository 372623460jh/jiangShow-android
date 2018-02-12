package com.jianghe.jiangshow;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by jjmatch on 2018/2/12.
 */

public class WebViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO 自动生成的方法存根
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_jiangshow);
        WebView web=(WebView) findViewById(R.id.jiangshow);
        web.loadUrl("file:///android_asset/pro/index.html");

        web.getSettings().setJavaScriptEnabled(true);  //加上这一行网页为响应式的
        web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;   //返回true， 立即跳转，返回false,打开网页有延时
            }
        });
        //支持App内部javascript交互

//        web.getSettings().setJavaScriptEnabled(true);

        //自适应屏幕

//        web.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);

//        web.getSettings().setLoadWithOverviewMode(true);

        //设置可以支持缩放

//        web.getSettings().setSupportZoom(true);

        //使用webview的视口
        web.getSettings().setUseWideViewPort(true);

        //设置是否出现缩放工具

//        web.getSettings().setBuiltInZoomControls(true);
    }
}

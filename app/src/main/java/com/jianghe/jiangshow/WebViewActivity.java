package com.jianghe.jiangshow;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jianghe.com.jianghe.startscreen.StartScreen;
import com.jianghe.common.commonMethod;

public class WebViewActivity extends Activity {

    WebView web = null;
    boolean isLoaded = false;
    static Activity currentActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        currentActivity = this;
        StartScreen.show(this, true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiangshow);

        /**
         * webview 部分
         */
        web = (WebView) findViewById(R.id.jiangshow);

        // 使用webview的视口
        web.getSettings().setUseWideViewPort(true);

        // 允许弹出alert
        web.setWebChromeClient(new WebChromeClient() {
            // 设置webview为ChromeClient
        });

        // 设置js可用
        web.getSettings().setJavaScriptEnabled(true);

        // 设置webview客户端
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回true， 立即跳转，返回false,打开网页有延时
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // 当js加载完毕回调方法
                super.onPageFinished(view, url);
                //在这里执行你想调用的js函数
                isLoaded = true;
            }
        });

        // 通过addJavascriptInterface()将Java对象映射到JS对象
        //参数1：Javascript对象名
        //参数2：Java对象名
        web.addJavascriptInterface(new commonMethod(), "JsInterface");//AndroidtoJS类对象映射到js的test对象

        // 加载url
        web.loadUrl("file:///android_asset/pro/index.html");

    }

    /**
     * 调用js方法的方法
     *
     * @param methodName window下的方法名
     * @param params     需要传递的参数（json字符串）
     */
    public void callJavascript(String methodName, String params) {
        web.evaluateJavascript("javascript:" + methodName + "('" + params + "')", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                //此处为 js 返回的结果
            }
        });
    }

    /**
     * 返回时执行
     */
    public void onBackPressed() {
        if (isLoaded) {
            // 调用js返回控制器
            WebViewActivity.this.callJavascript("backHandler", "");
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 获取当前上下文对象的静态方法
     *
     * @return
     */
    public static Activity getCurrentActivity() {
        return currentActivity;
    }
}
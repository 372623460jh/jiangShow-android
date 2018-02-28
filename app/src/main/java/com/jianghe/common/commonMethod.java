package com.jianghe.common;

import android.content.Intent;
import android.webkit.JavascriptInterface;

import com.jianghe.com.jianghe.startscreen.StartScreen;
import com.jianghe.jiangshow.WebViewActivity;

/**
 * Created by jianghe on 2018/2/28.
 * * 供js调用的Android方法
 */
public class commonMethod extends Object {

    // 定义JS需要调用的方法
    // 被JS调用的方法必须加入@JavascriptInterface注解

    // 关闭应用的方法
    @JavascriptInterface
    public void exit() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        WebViewActivity.getCurrentActivity().startActivity(intent);
        System.exit(0);
    }

    //关闭webview的方法
    @JavascriptInterface
    public void closeWebview() {
        //关闭webview的activity
        WebViewActivity.getCurrentActivity().finish();
    }

    /**
     * 打开启动屏
     */
    @JavascriptInterface
    public void showStartScreen() {
        StartScreen.show(WebViewActivity.getCurrentActivity());
    }

    /**
     * 关闭启动屏
     */
    @JavascriptInterface
    public void hideStartScreen() {
        StartScreen.hide(WebViewActivity.getCurrentActivity());
    }
}

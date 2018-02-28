package com.jianghe.com.jianghe.startscreen;

import android.app.Activity;
import android.app.Dialog;

import com.jianghe.jiangshow.R;

import java.lang.ref.WeakReference;

/**
 * StartScreen
 * 启动屏，用于防止白屏原理：
 * 当splashScreen向webview页面跳转后先启动一个和splashScreen相同图片的遮罩（Dialog），
 * 当webview中的页面加载完成后，主动调用原生关闭遮罩（Dialog）的方法来关闭遮罩（Dialog）,
 * 从而防止闪屏。
 */
public class StartScreen {
    private static Dialog mSplashDialog;
    private static WeakReference<Activity> mActivity;

    /**
     * 打开启动屏
     */
    public static void show(final Activity activity, final boolean fullScreen) {
        if (activity == null) return;
        mActivity = new WeakReference<Activity>(activity);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!activity.isFinishing()) {

                    mSplashDialog = new Dialog(activity, fullScreen ? R.style.StartFullscreen : R.style.StartTheme);
                    mSplashDialog.setContentView(R.layout.start_screen);
                    mSplashDialog.setCancelable(false);

                    if (!mSplashDialog.isShowing()) {
                        mSplashDialog.show();
                    }
                }
            }
        });
    }

    /**
     * 打开启动屏
     */
    public static void show(final Activity activity) {
        show(activity, false);
    }

    /**
     * 关闭启动屏
     */
    public static void hide(Activity activity) {
        if (activity == null) activity = mActivity.get();
        if (activity == null) return;

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mSplashDialog != null && mSplashDialog.isShowing()) {
                    mSplashDialog.dismiss();
                }
                mSplashDialog = null;
                mActivity = null;
            }
        });
    }
}

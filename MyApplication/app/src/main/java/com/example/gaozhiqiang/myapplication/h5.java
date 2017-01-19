package com.example.gaozhiqiang.myapplication;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static android.content.ContentValues.TAG;

/**
 * Created by gaozhiqiang on 2017/1/19.
 */

public class h5 {
    private Context mContext;
    private WebView wv;
    private Class r;
    public h5(Context ct,WebView wb) throws ClassNotFoundException, NoSuchMethodException {
        mContext = ct;
        wv=wb;
        //获取设置
        WebSettings webSettings = wv.getSettings();
        //允许使用js
        webSettings.setJavaScriptEnabled(true);
        //给js注入包，名字统一使用ZTX
        wv.addJavascriptInterface(new JsInteration(ct), "ZTX");
        r=ct.getClass();
    };

    //安卓调用js
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void call(final String js){
        wv.post(new Runnable() {
            @Override
            public void run() {
                wv.evaluateJavascript(js, null);
            }
        });
    }

    //注入给js调用的安卓方法
    public class JsInteration {
        //关联上下文
        Context bContext;
        JsInteration(Context c) {
            bContext = c;
        }
        //js准备完成是调用
        @JavascriptInterface
        public void callApp(String fnName,String fnData) throws NoSuchMethodException {
            Log.d(TAG, "callApp:"+fnName);
            Method method =  r.getMethod(fnName, String.class);
            try {
                method.invoke(mContext, fnData);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}

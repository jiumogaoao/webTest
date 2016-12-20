package com.example.gaozhiqiang.myapplication;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by gaozhiqiang on 2016/12/20.
 */

public class h5 {
    public WebView web;
    public h5(Context context,int w,int h,String url){
        this.web = new WebView(context);
        this.web.setLayoutParams(new ActionBar.LayoutParams(w,h));
        this.web.loadUrl("file:///android_asset/"+url+".html");
        WebSettings webSettings = this.web.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

}

package com.example.gaozhiqiang.myapplication;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    private WebView mWebView;
    private RelativeLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        h5resize re= new h5resize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = (RelativeLayout) findViewById(R.id.activity_main);
        h5 top = new h5(this,re.fullWidth,re.fullHeight,"test");
        layout.addView(top.web);
        top.web.addJavascriptInterface(new JsInteration(), "android");
    }
    //Android调用有返回值js方法
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void onClick(View v) {

        mWebView.evaluateJavascript("sum(1,2)", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                Log.e(TAG, "onReceiveValue value=" + value);
            }
        });
    }
    public class JsInteration {

        @JavascriptInterface
        public String back() {
            return "hello world";
        }
    }
}

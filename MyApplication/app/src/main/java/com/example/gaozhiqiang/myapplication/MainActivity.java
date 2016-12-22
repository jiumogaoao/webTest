package com.example.gaozhiqiang.myapplication;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    private WebView web;
    private String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        data="[{id:'001',title:'冠心病',icon:'#',dsc:'2016全球精准医疗（中国）峰会圆满召开！',list:[{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']}]},{id:'002',title:'冠心病',icon:'#',dsc:'2016全球精准医疗（中国）峰会圆满召开！',list:[{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']}]},{id:'003',title:'冠心病',icon:'#',dsc:'2016全球精准医疗（中国）峰会圆满召开！',list:[{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']}]},{id:'004',title:'冠心病',icon:'#',dsc:'2016全球精准医疗（中国）峰会圆满召开！',list:[{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']}]},{id:'005',title:'冠心病',icon:'#',dsc:'2016全球精准医疗（中国）峰会圆满召开！',list:[{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']}]},{id:'006',title:'冠心病',icon:'#',dsc:'2016全球精准医疗（中国）峰会圆满召开！',list:[{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']}]},{id:'007',title:'冠心病',icon:'#',dsc:'2016全球精准医疗（中国）峰会圆满召开！',list:[{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']}]},{id:'008',title:'冠心病',icon:'#',dsc:'2016全球精准医疗（中国）峰会圆满召开！',list:[{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']}]},{id:'009',title:'冠心病',icon:'#',dsc:'2016全球精准医疗（中国）峰会圆满召开！',list:[{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']}]},{id:'010',title:'冠心病',icon:'#',dsc:'2016全球精准医疗（中国）峰会圆满召开！',list:[{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']}]},{id:'011',title:'冠心病',icon:'#',dsc:'2016全球精准医疗（中国）峰会圆满召开！',list:[{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']}]}]";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        web = (WebView) findViewById(R.id.web);
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        web.addJavascriptInterface(new JsInteration(this), "ZTX");
        web.loadUrl("file:///android_asset/h5/index.html");
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void call(final String js){
        web.post(new Runnable() {
            @Override
            public void run() {
                web.evaluateJavascript(js, null);
            }
        });
    }
    public class JsInteration {
        Context mContext;
        JsInteration(Context c) {
            mContext = c;
        }
        @JavascriptInterface
        public void load() {
            call("set("+data+")");
        }
        @JavascriptInterface
        public void go(String pid) {
            startActivity(new Intent(MainActivity.this,Main2Activity.class));
        }
    }
}

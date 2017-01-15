package com.example.gaozhiqiang.myapplication;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    //放webview
    private WebView web;
    //放数据
    private String data;
    public checkUpdate upDate;
    private String pathName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        upDate=new checkUpdate();
        try {
            upDate.update(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        pathName=this.getFilesDir().getParent();
        //模拟拿到数据了
        data="[{id:'001',title:'冠心病',icon:'#',dsc:'2016全球精准医疗（中国）峰会圆满召开！',list:[{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']}]},{id:'002',title:'冠心病',icon:'#',dsc:'2016全球精准医疗（中国）峰会圆满召开！',list:[{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']}]},{id:'003',title:'冠心病',icon:'#',dsc:'2016全球精准医疗（中国）峰会圆满召开！',list:[{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']}]},{id:'004',title:'冠心病',icon:'#',dsc:'2016全球精准医疗（中国）峰会圆满召开！',list:[{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']}]},{id:'005',title:'冠心病',icon:'#',dsc:'2016全球精准医疗（中国）峰会圆满召开！',list:[{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']}]},{id:'006',title:'冠心病',icon:'#',dsc:'2016全球精准医疗（中国）峰会圆满召开！',list:[{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']}]},{id:'007',title:'冠心病',icon:'#',dsc:'2016全球精准医疗（中国）峰会圆满召开！',list:[{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']}]},{id:'008',title:'冠心病',icon:'#',dsc:'2016全球精准医疗（中国）峰会圆满召开！',list:[{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']}]},{id:'009',title:'冠心病',icon:'#',dsc:'2016全球精准医疗（中国）峰会圆满召开！',list:[{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']}]},{id:'010',title:'冠心病',icon:'#',dsc:'2016全球精准医疗（中国）峰会圆满召开！',list:[{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']}]},{id:'011',title:'冠心病',icon:'#',dsc:'2016全球精准医疗（中国）峰会圆满召开！',list:[{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']}]}]";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //通过id找到webview
        web = (WebView) findViewById(R.id.web);
        //获取设置
        WebSettings webSettings = web.getSettings();
        //允许使用js
        webSettings.setJavaScriptEnabled(true);
        //给js注入包，名字统一使用ZTX
        web.addJavascriptInterface(new JsInteration(this), "ZTX");
    }
    public void upDateEnd(){
        //加载所需页面file:///android_asset/为asset文件夹
        web.loadUrl("file://"+pathName+"/app_h5/index.html");
    }
    //安卓调用js
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void call(final String js){
        web.post(new Runnable() {
            @Override
            public void run() {
                web.evaluateJavascript(js, null);
            }
        });
    }
    //注入给js调用的安卓方法
    public class JsInteration {
        //关联上下文
        Context mContext;
        JsInteration(Context c) {
            mContext = c;
        }
        //js准备完成是调用
        @JavascriptInterface
        public void load() {
            call("set("+data+")");
        }
        //跳页时调用
        @JavascriptInterface
        public void go(String pid) {
            startActivity(new Intent(MainActivity.this,Main2Activity.class));
        }
    }


}

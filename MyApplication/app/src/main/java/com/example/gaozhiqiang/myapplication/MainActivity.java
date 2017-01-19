package com.example.gaozhiqiang.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    private h5 h5Controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            upDate=new checkUpdate(this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        pathName=this.getFilesDir().getParent();
        //模拟拿到数据了
        data="[{id:'001',title:'冠心病',icon:'#',dsc:'2016全球精准医疗（中国）峰会圆满召开！',list:[{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']}]},{id:'002',title:'冠心病',icon:'#',dsc:'2016全球精准医疗（中国）峰会圆满召开！',list:[{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']}]},{id:'003',title:'冠心病',icon:'#',dsc:'2016全球精准医疗（中国）峰会圆满召开！',list:[{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']}]},{id:'004',title:'冠心病',icon:'#',dsc:'2016全球精准医疗（中国）峰会圆满召开！',list:[{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']}]},{id:'005',title:'冠心病',icon:'#',dsc:'2016全球精准医疗（中国）峰会圆满召开！',list:[{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']}]},{id:'006',title:'冠心病',icon:'#',dsc:'2016全球精准医疗（中国）峰会圆满召开！',list:[{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']}]},{id:'007',title:'冠心病',icon:'#',dsc:'2016全球精准医疗（中国）峰会圆满召开！',list:[{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']}]},{id:'008',title:'冠心病',icon:'#',dsc:'2016全球精准医疗（中国）峰会圆满召开！',list:[{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']}]},{id:'009',title:'冠心病',icon:'#',dsc:'2016全球精准医疗（中国）峰会圆满召开！',list:[{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']}]},{id:'010',title:'冠心病',icon:'#',dsc:'2016全球精准医疗（中国）峰会圆满召开！',list:[{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']}]},{id:'011',title:'冠心病',icon:'#',dsc:'2016全球精准医疗（中国）峰会圆满召开！',list:[{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:0,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:'#'},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']},{id:'',type:1,title:'健康医疗科技精准对接会暨陕西省国家临床医学研究中心',praise:0,time:'2016/12/06',icon:['#','#','#']}]}]";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //通过id找到webview
        web = (WebView) findViewById(R.id.web);
        try {
            h5Controller = new h5(this,web);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    public void upDateEnd(){
        //加载所需页面file:///android_asset/为asset文件夹
        Log.d(TAG, "upDateEnd: upDateEnd");
        web.loadUrl("file://"+pathName+"/app_h5/index.html");
    }
    //js准备完成是调用
    public void load(String pid) {
        h5Controller.call("set("+data+")");
    }
    //跳页时调用
    public void go(String pid) {
        startActivity(new Intent(MainActivity.this,Main2Activity.class));
    }




}

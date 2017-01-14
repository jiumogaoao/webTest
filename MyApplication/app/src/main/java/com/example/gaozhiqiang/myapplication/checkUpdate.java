package com.example.gaozhiqiang.myapplication;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;



/**
 * Created by gaozhiqiang on 2017/1/13.
 */

public class checkUpdate {
    private String pathName;
    private Context con;
    private String result;
    private int newVersion;
    private String path="http://192.168.1.139";
    private final String TAG = "checkUpdate";
    public  void update(Context updateCon){
        con=updateCon;
        pathName=con.getFilesDir().getParent();
        deleteFolder(new File(pathName + "/app_download"));
        deleteFolder(new File(pathName + "/app_h5"));
        new Thread(getThread).start();
       // doDownLoadWork();
    }
    public  void deleteFolder(File file) {
        if (!file.exists())
            return;

        if (file.isDirectory()) {
            File files[] = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                deleteFolder(files[i]);
            }
        }
        file.delete();
    }
    public void doZipExtractorWork(){
        //ZipExtractorTask task = new ZipExtractorTask("/storage/usb3/system.zip", "/storage/emulated/legacy/", this, true);
        File file=con.getDir("h5", Context.MODE_PRIVATE);
        String zipPath=file.getAbsolutePath();
        ZipExtractorTask task = new ZipExtractorTask(pathName+"/app_download/"+newVersion+".zip",zipPath, con, true);
        ((MainActivity)con).upDateEnd();
        task.execute();
    }

    private void doDownLoadWork(){
        File file=con.getDir("download", Context.MODE_PRIVATE);
        String filePath=file.getAbsolutePath();
        DownLoaderTask task = new DownLoaderTask(path+"/down/"+newVersion+".zip", filePath, con);
        //DownLoaderTask task = new DownLoaderTask("http://192.168.9.155/johnny/test.h264", getCacheDir().getAbsolutePath()+"/", this);
        task.execute();
    }

    private Thread getThread = new Thread(){
        public void run() {
            HttpURLConnection connection = null;
            try {
                URL url = new URL(path+"?version=0");
                connection = (HttpURLConnection) url.openConnection();
                // 设置请求方法，默认是GET
                connection.setRequestMethod("GET");
                // 设置字符集
                connection.setRequestProperty("Charset", "UTF-8");
                // 设置文件类型
                connection.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
                // 设置请求参数，可通过Servlet的getHeader()获取
                connection.setRequestProperty("Cookie", "AppName=" + URLEncoder.encode("你好", "UTF-8"));
                // 设置自定义参数
                connection.setRequestProperty("MyProperty", "this is me!");

                if(connection.getResponseCode() == 200){
                    InputStream is = connection.getInputStream();
                    result = convertStreamToString(is);

                    Message msg = Message.obtain();
                    msg.what = 0;
                    getHandler.sendMessage(msg);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                if(connection != null){
                    connection.disconnect();
                }
            }
        };
    };

    private Handler getHandler;

    {
        getHandler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 0 && result != null) {
                    try {
                        JSONObject jsonObject2 = new JSONObject(result);
                        newVersion = jsonObject2.getInt("version");
                        if(newVersion!=0){
                            doDownLoadWork();
                        }else{
                            ((MainActivity)con).upDateEnd();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            ;
        };
    }

    public static String convertStreamToString(InputStream is) {
        /*
          * To convert the InputStream to String we use the BufferedReader.readLine()
          * method. We iterate until the BufferedReader return null which means
          * there's no more data to read. Each line will appended to a StringBuilder
          * and returned as String.
          */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }

}

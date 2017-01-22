package com.example.gaozhiqiang.myapplication.tool;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import static com.example.gaozhiqiang.myapplication.MainActivity.TAG;
import static com.example.gaozhiqiang.myapplication.checkUpdate.convertStreamToString;

/**
 * Created by gaozhiqiang on 2017/1/20.
 */

public class NetworkTool {
    private static Handler callHandler= new Handler();

    public static void get(final String path, final Object con, final String callback){
        new Thread(){
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(path);
                    connection = (HttpURLConnection) url.openConnection();
                    // 设置请求方法，默认是GET
                    connection.setRequestMethod("GET");
                    // 设置字符集
                    connection.setRequestProperty("Charset", "UTF-8");
                    // 设置文件类型
                    connection.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");

                    if(connection.getResponseCode() == 200){
                        InputStream is = connection.getInputStream();
                        String result = convertStreamToString(is);

                        final JSONObject resultJSON = new JSONObject(result);

                        callHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                Class inClass=con.getClass();
                                Method method = null;
                                try {
                                    method = inClass.getMethod(callback,JSONObject.class);
                                } catch (NoSuchMethodException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    method.invoke(con,resultJSON);
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (InvocationTargetException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    if(connection != null){
                        connection.disconnect();
                    }
                }
            };
        }.start();
    }
    public static void post(final String path, final String post, final Object con, final String callback){
        new Thread() {
            public void run() {
                URL url = null;
                try {
                    url = new URL(path);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");// 提交模式
                    // conn.setConnectTimeout(10000);//连接超时 单位毫秒
                    // conn.setReadTimeout(2000);//读取超时 单位毫秒
                    // 发送POST请求必须设置如下两行
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    // 获取URLConnection对象对应的输出流
                    PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
                    // 发送请求参数
                    printWriter.write(post);//post的参数 xx=xx&yy=yy
                    // flush输出流的缓冲
                    printWriter.flush();
                    //开始获取数据
                    BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    int len;
                    byte[] arr = new byte[1024];
                    while((len=bis.read(arr))!= -1){
                        bos.write(arr,0,len);
                        bos.flush();
                    }
                    bos.close();
                    String result = bos.toString("utf-8");
                    final JSONObject resultJSON = new JSONObject(result);
                    callHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Class inClass=con.getClass();
                            Method method = null;
                            try {
                                method = inClass.getMethod(callback,JSONObject.class);
                            } catch (NoSuchMethodException e) {
                                e.printStackTrace();
                            }
                            try {
                                method.invoke(con,resultJSON);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    public static void downLoadFromUrl(final String urlStr, final String fileName, final String savePath, final Object con, final String callback) throws IOException {
        new Thread() {
            public void run() {
                URL url = null;
                try {
                    url = new URL(urlStr);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                HttpURLConnection conn = null;
                try {
                    conn = (HttpURLConnection) url.openConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //设置超时间为3秒
                conn.setConnectTimeout(3 * 1000);
                //防止屏蔽程序抓取而返回403错误
                conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

                //得到输入流
                InputStream inputStream = null;
                try {
                    inputStream = conn.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //获取自己数组
                byte[] getData = new byte[0];
                try {
                    getData = readInputStream(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //文件保存位置
                File saveDir = new File(savePath);
                if (!saveDir.exists()) {
                    saveDir.mkdir();
                }
                File file = new File(saveDir + File.separator + fileName);
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    fos.write(getData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("info:" + url + " download success");
                callHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Class inClass=con.getClass();
                        Method method = null;
                        try {
                            method = inClass.getMethod(callback);
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                        try {
                            method.invoke(con);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }.start();

    }

    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    /* 上传文件至Server的方法 */
    public static void uploadFile(final String newName, final String uploadFile, final String actionUrl, final Object conObject, final String callback)
    {
        new Thread() {
            public void run() {
                String end ="\r\n";
                String twoHyphens ="--";
                String boundary ="*****";
                try
                {
                    URL url =new URL(actionUrl);
                    final HttpURLConnection con=(HttpURLConnection)url.openConnection();
          /* 允许Input、Output，不使用Cache */
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    con.setUseCaches(false);
          /* 设置传送的method=POST */
                    con.setRequestMethod("POST");
          /* setRequestProperty */
                    con.setRequestProperty("Connection", "Keep-Alive");
                    con.setRequestProperty("Charset", "UTF-8");
                    con.setRequestProperty("Content-Type",
                            "multipart/form-data;boundary="+boundary);
          /* 设置DataOutputStream */
                    DataOutputStream ds =
                            new DataOutputStream(con.getOutputStream());
                    ds.writeBytes(twoHyphens + boundary + end);
                    ds.writeBytes("Content-Disposition: form-data; "+
                            "name=\"file1\";filename=\""+
                            newName +"\""+ end);
                    ds.writeBytes(end);
          /* 取得文件的FileInputStream */
                    FileInputStream fStream =new FileInputStream(uploadFile);
          /* 设置每次写入1024bytes */
                    int bufferSize =1024;
                    byte[] buffer =new byte[bufferSize];
                    int length =-1;
          /* 从文件读取数据至缓冲区 */
                    while((length = fStream.read(buffer)) !=-1)
                    {
            /* 将资料写入DataOutputStream中 */
                        ds.write(buffer, 0, length);
                    }
                    ds.writeBytes(end);
                    ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
          /* close streams */
                    fStream.close();
                    ds.flush();
          /* 取得Response内容 */
                    InputStream is = con.getInputStream();
                    int ch;
                    StringBuffer b =new StringBuffer();
                    while( ( ch = is.read() ) !=-1 )
                    {
                        b.append( (char)ch );
                    }
          /* 将Response显示于Dialog */
                    Log.d(TAG, "上传成功"+b.toString().trim());
          /* 关闭DataOutputStream */
                    ds.close();
                    callHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Class inClass=conObject.getClass();
                            Method method = null;
                            try {
                                method = inClass.getMethod(callback);
                            } catch (NoSuchMethodException e) {
                                e.printStackTrace();
                            }
                            try {
                                method.invoke(conObject);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                catch(Exception e)
                {
                    Log.d(TAG, "uploadFile: "+e);
                }
            }
        }.start();

    }


}

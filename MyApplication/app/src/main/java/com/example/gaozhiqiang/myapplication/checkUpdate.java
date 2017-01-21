package com.example.gaozhiqiang.myapplication;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
    private String path="http://192.168.1.102";
    private final String TAG = "checkUpdate";
    private String localVersion;
    private String versionFileName;
    private File versionFile;
    private Class inClass;

    public  checkUpdate(Context updateCon) throws IOException, ClassNotFoundException {
        con=updateCon;
        inClass=updateCon.getClass();
        pathName=con.getFilesDir().getParent();

        versionFileName = con.getFilesDir().getAbsolutePath() + "/h5Version";
        versionFile = new File(versionFileName);
        if (!versionFile.exists()) {
            try {
                //在指定的文件夹中创建文件
                versionFile.createNewFile();
                localVersion = "0";
                FileWriter fw = new FileWriter(versionFileName, true);//
                // 创建FileWriter对象，用来写入字符流
                BufferedWriter bw = new BufferedWriter(fw); // 将缓冲对文件的输出
                bw.write(localVersion);
                bw.close();
                fw.close();
            } catch (Exception e) {
            }
        }else{
            FileInputStream in = null;
            ByteArrayOutputStream bout = null;
            byte[]buf = new byte[1024];
            bout = new ByteArrayOutputStream();
            int length = 0;
            in = new FileInputStream(versionFileName); //获得输入流
            while((length=in.read(buf))!=-1){
                bout.write(buf,0,length);
            }
            byte[] versionContent = bout.toByteArray();
            localVersion = new String(versionContent,"UTF-8");
            Log.d(TAG, "localVersion: "+localVersion);
            in.close();
            bout.close();
        }
        new Thread(getThread).start();
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
    public void doZipExtractorWork() throws NoSuchMethodException {
        File file=con.getDir("h5", Context.MODE_PRIVATE);
        String zipPath=file.getAbsolutePath();
        ZipExtractorTask task = new ZipExtractorTask(pathName+"/app_download/"+newVersion+".zip",zipPath, con, true);

        Method method =  inClass.getMethod("upDateEnd");
        try {
            method.invoke(con);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        task.execute();
    }

    private void doDownLoadWork() throws ClassNotFoundException {
        File file=con.getDir("download", Context.MODE_PRIVATE);
        String filePath=file.getAbsolutePath();
        DownLoaderTask task = new DownLoaderTask(path+"/down/"+newVersion+".zip", filePath, con,this);
        task.execute();
    }

    private Thread getThread = new Thread(){
        public void run() {
            HttpURLConnection connection = null;
            try {
                URL url = new URL(path+"?version="+localVersion);
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
                            versionFile.delete();
                            versionFile = new File(versionFileName);
                            FileWriter fw = new FileWriter(versionFileName, true);//
                            // 创建FileWriter对象，用来写入字符流
                            BufferedWriter bw = new BufferedWriter(fw); // 将缓冲对文件的输出
                            bw.write(String.valueOf(newVersion));
                            bw.close();
                            fw.close();
                            deleteFolder(new File(pathName + "/app_download"));
                            deleteFolder(new File(pathName + "/app_h5"));
                            doDownLoadWork();
                        }else{
                            Method method =  inClass.getMethod("upDateEnd");
                            try {
                                method.invoke(con);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
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

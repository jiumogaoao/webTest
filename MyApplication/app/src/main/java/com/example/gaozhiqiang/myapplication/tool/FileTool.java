package com.example.gaozhiqiang.myapplication.tool;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by jiumogaoo on 2017/1/21.
 */

public class FileTool {
    private static Handler callHandler= new Handler();
    public static void get(final String fileName, final Context con, final String callback) throws IOException {
        new Thread() {
            public void run() {
                try{
                    File getFile = new File(fileName);
                    String returnStr="";
                    if (getFile.exists()) {
                        FileInputStream in = null;
                        ByteArrayOutputStream bout = null;
                        byte[]buf = new byte[1024];
                        bout = new ByteArrayOutputStream();
                        int length = 0;
                        in = new FileInputStream(fileName); //获得输入流
                        while((length=in.read(buf))!=-1){
                            bout.write(buf,0,length);
                        }
                        byte[] stringContent = bout.toByteArray();
                        returnStr = new String(stringContent,"UTF-8");
                        in.close();
                        bout.close();
                    }
                    final String finalReturnStr = returnStr;
                    callHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                            Class inClass=con.getClass();
                            Method method = null;
                                method = inClass.getMethod(callback,String.class);
                                method.invoke(con, finalReturnStr);
                            } catch (NoSuchMethodException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();

    }
    public static void set(final String fileName, final String setData, final Context con, final String callback) throws IOException {
        new Thread() {
            public void run() {
                try {
                    File setFile = new File(fileName);
                    if (setFile.exists()) {
                        setFile.delete();
                    }
                    setFile.createNewFile();
                    FileWriter fw = new FileWriter(fileName, true);//
                    // 创建FileWriter对象，用来写入字符流
                    BufferedWriter bw = new BufferedWriter(fw); // 将缓冲对文件的输出
                    bw.write(setData);
                    bw.close();
                    fw.close();
                    callHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Class inClass=con.getClass();
                                Method method = null;
                                method = inClass.getMethod(callback);
                                method.invoke(con);
                            } catch (NoSuchMethodException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();


    }
}

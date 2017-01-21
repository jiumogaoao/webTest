package com.example.gaozhiqiang.myapplication.tool;

import android.content.Context;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by jiumogaoo on 2017/1/21.
 */

public class FileTool {
    public String get(Context con,String fileName) throws IOException {
        File getFile = new File(fileName);
        if (getFile.exists()) {
            String returnStr;
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
            return returnStr;
        }else{
            return "";
        }
    }
    public void set(Context con,String fileName,String setData) throws IOException {
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
    }
}

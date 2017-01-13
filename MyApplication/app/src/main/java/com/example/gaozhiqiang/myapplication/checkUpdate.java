package com.example.gaozhiqiang.myapplication;

import android.content.Context;

import java.io.File;

/**
 * Created by gaozhiqiang on 2017/1/13.
 */

public class checkUpdate {
    private String pathName;
    private Context con;
    public  void update(Context updateCon){
        con=updateCon;
        pathName=con.getFilesDir().getParent();
        deleteFolder(new File(pathName + "/app_download"));
        deleteFolder(new File(pathName + "/app_h5"));
        doDownLoadWork();
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
        ZipExtractorTask task = new ZipExtractorTask(pathName+"/app_download/3.zip",zipPath, con, true);
        ((MainActivity)con).upDateEnd();
        task.execute();
    }

    private void doDownLoadWork(){
        File file=con.getDir("download", Context.MODE_PRIVATE);
        String filePath=file.getAbsolutePath();
        DownLoaderTask task = new DownLoaderTask("http://192.168.1.139/down/3.zip", filePath, con);
        //DownLoaderTask task = new DownLoaderTask("http://192.168.9.155/johnny/test.h264", getCacheDir().getAbsolutePath()+"/", this);
        task.execute();
    }
}

package com.example.gaozhiqiang.myapplication;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by gaozhiqiang on 2016/12/20.
 */

public class h5resize {
    public int fullWidth;
    public int fullHeight;
    public int size;
    public h5resize(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        fullWidth=dm.widthPixels;
        fullHeight=dm.heightPixels;
        size=(int)(fullWidth/750);
    }
}

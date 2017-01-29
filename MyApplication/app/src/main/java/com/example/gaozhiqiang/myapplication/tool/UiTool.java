package com.example.gaozhiqiang.myapplication.tool;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.gaozhiqiang.myapplication.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by jiumogaoo on 2017/1/26.
 */

public class UiTool {
    public static void alert(Context con,String mainText){
        Toast toast = Toast.makeText(con,mainText,Toast.LENGTH_LONG);
        toast.show();
    }
    public static void alertIcon(Context con,String mainText,int imgId){
        Toast toast = Toast.makeText(con,mainText,Toast.LENGTH_LONG);
        LinearLayout toast_layout = (LinearLayout)toast.getView();
        ImageView iv = new ImageView(con);
        iv.setImageResource(imgId);
        toast_layout.addView(iv,0);
        toast.show();
    }
    public static void confirm(final Context con, String title, int imgId, String message, String buttonAName, final String buttonACallback, String buttonBName, final String buttonBCallback){
        AlertDialog.Builder builder = new AlertDialog.Builder(con);
        builder.setTitle(title);
        builder.setIcon(imgId);
        builder.setMessage(message);
        builder.setPositiveButton(buttonAName, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                try{
                    Class inClass=con.getClass();
                    Method method = null;
                    method = inClass.getMethod(buttonACallback);
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
        builder.setNegativeButton(buttonBName,  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                try{
                    Class inClass=con.getClass();
                    Method method = null;
                    method = inClass.getMethod(buttonBCallback);
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
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

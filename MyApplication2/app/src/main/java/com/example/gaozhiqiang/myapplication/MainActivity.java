package com.example.gaozhiqiang.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {
    private String TAG="log";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            Class r=Class.forName("com.example.gaozhiqiang.myapplication.man");
            Constructor constructor =  r.getConstructor((Class<?>[]) null);
            Object a=constructor.newInstance();
            Method method =  r.getMethod("name", null);
            String w= (String) method.invoke(a, null);
            Log.d(TAG, "onCreate: "+w);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

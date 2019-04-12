package com.example.process_tool;

import android.app.Activity;
import android.content.Context;

import java.lang.reflect.Method;

public class AptTool {

    public static void showToast(Context context, String message) {
        try {
            Class testClass = Class.forName("com.example.za399.aptdemo.ToastUtil");
            Method method = testClass.getMethod("showToast", Class.forName("android.content.Context"), message.getClass());
            method.invoke(null, context, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void bind(Activity target) {
        Class clazz = target.getClass();
        try {
            Class bindViewClass = Class.forName(clazz.getName() + "_ViewBinding");
            Method method = bindViewClass.getMethod("bind", target.getClass());
            method.invoke(bindViewClass.newInstance(), target);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

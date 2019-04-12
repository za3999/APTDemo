package com.example.process_lib.util;

import java.lang.reflect.Method;

public class AptTool {

    public static void test() {
        try {
            Class bindViewClass = Class.forName("com.test.apt.HelloWorld");
            Method method = bindViewClass.getMethod("main");
            method.invoke(null, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void bind(Object activity){
        Class clazz = activity.getClass();
        try {
            Class bindViewClass = Class.forName(clazz.getName() + "_ViewBinding");
            Method method = bindViewClass.getMethod("bind", activity.getClass());
            method.invoke(bindViewClass.newInstance(), activity);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

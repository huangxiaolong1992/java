package com.example.myblog.common;

public class ThreadLocalRequest {
   private static ThreadLocal localVar = new ThreadLocal();

    public static void setInfo(String msg) {
        localVar.set(msg);
    }

    public static String getInfo() {
        return (String) localVar.get();
    }

    public static void removeInfo() {
        localVar.remove();
    }

}

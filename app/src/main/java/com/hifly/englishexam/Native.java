package com.hifly.englishexam;

public class Native {
    static {
        System.loadLibrary("native-lib");
    }

    public native void print();
}

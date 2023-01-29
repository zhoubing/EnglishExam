//
// Created by zhoubing on 2023/1/10.
//
#include <jni.h>
#include "com_hifly_englishexam_Native.h"
#include <android/log.h>
#include "qtandroid.h"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, "keymatch", __VA_ARGS__)

extern "C" JNIEXPORT void JNICALL Java_com_hifly_englishexam_Native_print(JNIEnv *, jobject)
{
    LOGD("Hello World from native-lib!!!!");
    QtAndroid().Print();
}
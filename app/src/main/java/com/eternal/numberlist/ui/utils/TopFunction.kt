package com.eternal.numberlist.ui.utils

import android.os.Handler
import android.os.Looper

fun runOnMainThread(runnable: Runnable){
    Handler(Looper.getMainLooper()).post(runnable)
}
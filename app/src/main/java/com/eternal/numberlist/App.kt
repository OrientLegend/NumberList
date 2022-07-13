package com.eternal.numberlist

import android.app.Application
import android.app.Service
import android.os.Build
import android.os.Vibrator
import android.os.VibratorManager
import androidx.annotation.RequiresApi
import com.tencent.mmkv.MMKV

class App: Application() {

    companion object{
        lateinit var mmkv: MMKV
        lateinit var vibrator: VibratorManager
    }


    override fun onCreate() {
        super.onCreate()
        MMKV.initialize(this)
        mmkv = MMKV.defaultMMKV()!!
        vibrator = applicationContext.getSystemService(Service.VIBRATOR_MANAGER_SERVICE)
                        as VibratorManager
    }
}
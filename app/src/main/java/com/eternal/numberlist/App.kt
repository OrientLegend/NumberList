package com.eternal.numberlist

import android.annotation.SuppressLint
import android.app.Application
import android.app.Service
import android.content.Context
import android.os.Build
import android.os.Vibrator
import android.os.VibratorManager
import androidx.annotation.RequiresApi
import com.eternal.numberlist.database.AppDatabase
import com.tencent.mmkv.MMKV
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {

    companion object{
        lateinit var mmkv: MMKV
        lateinit var vibrator: VibratorManager
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        lateinit var appDatabase: AppDatabase
    }


    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        appDatabase = AppDatabase.getDatabase(this)
        MMKV.initialize(this)
        mmkv = MMKV.defaultMMKV()!!
        vibrator = applicationContext.getSystemService(Service.VIBRATOR_MANAGER_SERVICE)
                        as VibratorManager
    }
}
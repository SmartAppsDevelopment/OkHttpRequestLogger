package com.example.apilogsampleproject

import android.app.Application
import com.rv.apilog.apiclient.settting.LoggerApiSetting

/**
 * @author Umer Bilal
 * Created 1/12/2023 at 3:06 PM
 */
class MainApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        LoggerApiSetting.enableShakeListener(this)
    }
}
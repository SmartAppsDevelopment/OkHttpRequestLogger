package com.rv.apilog.apiclient.settting

import android.app.Application
import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.content.Intent
import android.hardware.SensorManager
import com.rv.apilog.ApiListActivity
import com.rv.apilog.BuildConfig
import com.squareup.seismic.ShakeDetector


/**
 * @author Umer Bilal
 * Created 1/12/2023 at 2:48 PM
 */
object LoggerApiSetting {

    var enableShakeForReleaseApp = false

    fun enableShakeListener(application: Context) {
        val sensorManager = application.getSystemService(SENSOR_SERVICE) as SensorManager
        ShakeDetector {
            if (BuildConfig.DEBUG) {
                openActivity(application)
            } else if (enableShakeForReleaseApp) {
                openActivity(application)
            }
        }.start(sensorManager)

    }

     fun openActivity(application: Context) {
        application.startActivity(
            Intent(
                application,
                ApiListActivity::class.java
            ).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            })
    }
}
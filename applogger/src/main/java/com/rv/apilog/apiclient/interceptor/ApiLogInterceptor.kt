package com.rv.apilog.apiclient.interceptor

import android.util.Log
import com.rv.apilog.ApiResponse
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Buffer
import java.io.IOException
import java.text.SimpleDateFormat

object ApiLogInterceptor : Interceptor {
    val TAG = ApiLogInterceptor::class.java.simpleName
    override fun intercept(chain: Interceptor.Chain): Response {

        val apiData = ApiResponse()
        Log.d(TAG, "inside intercept callback")
        val request = chain.request()
        val t1 = System.nanoTime()
        var requestLog = java.lang.String.format(
            "Sending request %s on %s%n%s",
            request.url, chain.connection(), request.headers
        )
        if (request.method.equals("post", true)) {
            requestLog = """
                    $requestLog
                    ${bodyToString(request)}
                    """.trimIndent()
        }
        apiData.requestBody = requestLog
        apiData.headers = request.headers.toString()
        apiData.requestMethod = request.method
        apiData.requestUrl = request.url.toString()
        apiData.hitTimeStemp = " "+SimpleDateFormat("hh:mm:ss").format(System.currentTimeMillis())

        Log.d(TAG, "request\n$requestLog")
        val response: Response = chain.proceed(request)
        val t2 = System.nanoTime()
        Log.d(TAG, "intercept: response Code ===> ${response.code}")
        val responseLog = String.format(
            "Received response for %s in %.1fms%n%s",
            response.request.url,
            (t2 - t1) / 1e6,
            response.headers
        )


        val bodyString: String? = response.body?.string()

        Log.d(TAG, "response only\n$bodyString")

        Log.d(TAG, "response\n$responseLog\n$bodyString")
        val doubleres=((t2 - t1) / 1e6)
        apiData.responseTime = String.format("(%.4fs)",doubleres/3600)
        apiData.responseCode = response.code
        apiData.responseLog = responseLog
        apiData.responseBody = bodyString ?: ""
        ApiResponse.listOfResponses.add(apiData)
        return response.newBuilder()
            .body((bodyString ?: "").toResponseBody(response.body?.contentType()))
            .build()
    }


    private fun bodyToString(request: Request): String {
        return try {
            val copy = request.newBuilder().build()
            val buffer = Buffer()
            copy.body?.writeTo(buffer)
            buffer.readUtf8()
        } catch (e: IOException) {
            "did not work"
        }
    }
}
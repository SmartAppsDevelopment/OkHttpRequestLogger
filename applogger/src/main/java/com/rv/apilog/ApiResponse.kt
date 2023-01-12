package com.rv.apilog

import okhttp3.Headers
import java.io.Serializable

class ApiResponse : Serializable {
    var responseLog: String = ""
    var responseBody: String = ""
    var responseCode: Int = 0
    var requestUrl: String = ""
    var requestMethod: String = ""
    var headers: String? = null
    var responseTime: String =" 0.0"
    var hitTimeStemp: String=""
    var requestBody: String = ""


    companion object{
        var listOfResponses = ArrayList<ApiResponse>()
    }

    override fun toString(): String {
        return "ApiResponse(responseLog='$responseLog', responseBody='$responseBody', responseCode=$responseCode, requestUrl='$requestUrl', requestMethod='$requestMethod', headers=$headers, responseTime=$responseTime, requestBody='$requestBody')"
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = responseLog.hashCode()
        result = 31 * result + responseBody.hashCode()
        result = 31 * result + responseCode
        result = 31 * result + requestUrl.hashCode()
        result = 31 * result + requestMethod.hashCode()
        result = 31 * result + (headers?.hashCode() ?: 0)
        result = 31 * result + responseTime.hashCode()
        result = 31 * result + requestBody.hashCode()
        return result
    }
}


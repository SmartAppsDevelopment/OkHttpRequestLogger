package com.example.apilogsampleproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import retrofit2.Callback
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.example.apilogsampleproject.apiref.APIClient
import com.example.apilogsampleproject.apiref.ApiInterface
import com.example.apilogsampleproject.mode.CatFacts
import com.rv.apilog.apiclient.settting.LoggerApiSetting
import retrofit2.Call
import retrofit2.Response

class SampleShowCase : AppCompatActivity() {


    lateinit var pbCats : ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_showcase)

        pbCats = findViewById(com.rv.apilog.R.id.pbCats)


        findViewById<TextView>(com.rv.apilog.R.id.tvCatFacts).setOnClickListener {
            callApi()
        }

        findViewById<Button>(com.rv.apilog.R.id.btnShowResponse).setOnClickListener {
           LoggerApiSetting.openActivity(this@SampleShowCase)
        }

    }

    fun onButtonClick(view: View){
       // startActivity(Intent(this,MainActivity::class.java))
        //call
    }

    var apiInterface: ApiInterface? = null

    private fun callApi() {
        apiInterface = APIClient.client.create(ApiInterface::class.java)

        pbCats.visibility = View.VISIBLE

        val call2: Call<CatFacts> = apiInterface!!.getCatFacts()
        call2.enqueue(object : Callback<CatFacts> {
            override fun onResponse(call: Call<CatFacts>, response: Response<CatFacts>) {
                pbCats.visibility = View.GONE
            }

            override fun onFailure(call: Call<CatFacts>, t: Throwable) {
                pbCats.visibility = View.GONE
            }
        })


    }
}

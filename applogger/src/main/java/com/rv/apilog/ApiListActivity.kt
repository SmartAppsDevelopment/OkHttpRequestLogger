package com.rv.apilog

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.rv.apilog.apiclient.fragments.ApiResponsesDetailFragment
import com.rv.apilog.apiclient.fragments.ApiResponsesFragment

internal class ApiListActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api_list)
        supportActionBar?.hide()
        setTheme(R.style.Theme_ApiLog)
        funLoadApiListFragment()
        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            onBackPressed()
        }

    }

    private fun funLoadApiListFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.enter_from_left,
            R.anim.exit_to_right,
            R.anim.enter_from_right,
            R.anim.exit_to_left
        )
        transaction.replace(R.id.flMain, ApiResponsesFragment(), "TAG")
        transaction.commit()
    }

    fun loadApiDetailsFragment(response: ApiResponse) {

        findViewById<ImageView>(R.id.btnBack).visibility = View.VISIBLE

        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.enter_from_left,
            R.anim.exit_to_right,
            R.anim.enter_from_right,
            R.anim.exit_to_left
        )
        transaction.replace(R.id.flMain, ApiResponsesDetailFragment.newInstance(response))
            .addToBackStack("fg1")
        transaction.commit()

    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            findViewById<ImageView>(R.id.btnBack).visibility = View.GONE
        } else {
            super.onBackPressed()
        }
    }
}
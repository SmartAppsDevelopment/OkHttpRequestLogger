package com.rv.apilog.apiclient.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rv.apilog.ApiListActivity
import com.rv.apilog.ApiResponse
import com.rv.apilog.R
import com.rv.apilog.apiclient.adapter.ApiResponseAdapter
import com.rv.apilog.databinding.FragmentApiResponsesBinding


internal class ApiResponsesFragment : Fragment(R.layout.fragment_api_responses) {

    val mAdapter = ApiResponseAdapter {
        (activity as ApiListActivity).loadApiDetailsFragment(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireView().findViewById<RecyclerView>(R.id.rvApis).adapter = mAdapter
        mAdapter.submitList(ApiResponse.listOfResponses)
        requireView().findViewById<RecyclerView>(R.id.rvApis).smoothScrollToPosition(ApiResponse.listOfResponses.size)

    }

}
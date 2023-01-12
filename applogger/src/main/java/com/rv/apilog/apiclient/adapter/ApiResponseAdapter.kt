package com.rv.apilog.apiclient.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rv.apilog.ApiResponse
import com.rv.apilog.R

class ApiResponseAdapter(private var onItemClicked: (ApiResponse) -> Unit) : ListAdapter<ApiResponse, ApiResponseAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_apis, parent, false);
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClicked)
    }

    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun bind(
            response: ApiResponse,
            onItemClicked: (ApiResponse) -> Unit
        ) {

            view.findViewById<TextView>(R.id.tvApiResponse).text = "(${response.requestMethod})${response.responseCode} ->"
            view.findViewById<TextView>(R.id.tvUrl).text = "${response.requestUrl}"
            view.setOnClickListener {
                onItemClicked.invoke(response)
            }
            view.findViewById<TextView>(R.id.tv_timestemp).text=response.hitTimeStemp
            view.findViewById<TextView>(R.id.tv_timestemp_response).text=response.responseTime
        }
    }


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ApiResponse>() {
            override fun areItemsTheSame(
                oldItem: ApiResponse,
                newItem: ApiResponse
            ): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(
                oldItem: ApiResponse,
                newItem: ApiResponse
            ): Boolean =
                oldItem == newItem
        }
    }


}


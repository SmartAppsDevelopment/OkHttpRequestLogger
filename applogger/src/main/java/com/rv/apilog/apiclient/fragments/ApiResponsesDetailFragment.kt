package com.rv.apilog.apiclient.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.rv.apilog.ApiResponse
import com.rv.apilog.R


private const val ARG_PARAM1 = "param1"


internal class ApiResponsesDetailFragment : Fragment(R.layout.fragment_api_responses_detail) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val response = getResponse(arguments?.getSerializable(ARG_PARAM1) as ApiResponse?)
        view.findViewById<TextView>(R.id.tvApiDetails).text = response

        view.findViewById<Button>(R.id.btnShare).setOnClickListener { shareText(response) }
        view.findViewById<Button>(R.id.btnCopy).setOnClickListener {
            setClipboard(requireContext(), response)
        }
    }

    private fun setClipboard(context: Context, text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("ApiLogs", text)
        clipboard.setPrimaryClip(clip)

        Toast.makeText(requireContext(), "Text copied to clipboard", Toast.LENGTH_SHORT).show()
    }

    private fun shareText(text: String) {
        val intent = Intent(Intent.ACTION_SEND)
        /*This will be the actual content you wish you share.*/
        /*This will be the actual content you wish you share.*/
        val shareBody = text
        /*The type of the content is text, obviously.*/
        /*The type of the content is text, obviously.*/intent.type = "text/plain"
        /*Applying information Subject and Body.*/
        /*Applying information Subject and Body.*/intent.putExtra(
            Intent.EXTRA_SUBJECT,
            getString(R.string.app_name)
        )
        intent.putExtra(Intent.EXTRA_TEXT, shareBody)
        startActivity(Intent.createChooser(intent, "Share Using"))
    }

    private fun getResponse(response: ApiResponse?): String {

        if (response == null) {
            return "Error..."
        }


        val gson = GsonBuilder().setPrettyPrinting().create()
        val jp = JsonParser()
        val je: JsonElement = jp.parse(response.responseBody)
        val prettyJsonString = gson.toJson(je)

        val result =
            "${getHeading("Headers")} ${response.headers}" +
                    "${getHeading("Request Type")} ${response.requestMethod}" +
                    "${getHeading("Request httpBody")} ${response.requestBody}" +
                    "${getHeading("Response Body")} $prettyJsonString"

        return result

    }


    private fun getHeading(title: String) =
        "${getTripleLineBreak()}====$title====${getDoubleLineBreak()}"

    private fun getTripleLineBreak() = "\n\n\n"
    private fun getDoubleLineBreak() = "\n\n"

    fun String.getHeaderTittle() = "====$this===="

    companion object {
        fun newInstance(response: ApiResponse) = ApiResponsesDetailFragment().apply {
            arguments = Bundle().apply {
                putSerializable(ARG_PARAM1, response)
            }
        }
    }

}
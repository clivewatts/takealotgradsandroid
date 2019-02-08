package com.takealot.gradmoviesorter.api

import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.koushikdutta.ion.Ion
import com.takealot.gradmoviesorter.MainActivity
import com.takealot.gradmoviesorter.R
import com.takealot.gradmoviesorter.interfaces.Search

object ApiHelper {

    fun doTitleSearch(context: Context, url:String, term:String) {

        var useCaseReturn:Search = context as MainActivity
        //spaces to plus to conform to api
        val spaceSafeTerm = term.replace(" ", "+")
        val searchUrl = "$url$spaceSafeTerm"

        Ion.with(context).load(searchUrl).setLogging("Networking", Log.DEBUG).asJsonObject().withResponse().setCallback { e, result ->
            useCaseReturn.onTitleSearch(result.result)
        }

    }

    fun loadImageIntoView(context:Context, view:ImageView, url:String) {
        Ion.with(context)
            .load(url)
            .withBitmap()
            .intoImageView(view)
    }

}
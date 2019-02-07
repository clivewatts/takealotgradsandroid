package com.takealot.gradmoviesorter.api

import android.content.Context
import android.util.Log
import com.koushikdutta.ion.Ion
import com.takealot.gradmoviesorter.MainActivity
import com.takealot.gradmoviesorter.interfaces.Search

object ApiHelper {

    open fun doTitleSearch(context: Context, url:String, term:String) {

        var useCaseReturn:Search = context as MainActivity
        val spaceSafeTerm = term.replace(" ", "+")
        val searchUrl = "$url$spaceSafeTerm."
        Ion.with(context).load(searchUrl).setLogging("Networking", Log.DEBUG).asJsonObject().withResponse().setCallback { e, result ->
            useCaseReturn.onTitleSearch(result.result)
        }
    }

}
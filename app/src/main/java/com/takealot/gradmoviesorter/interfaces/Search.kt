package com.takealot.gradmoviesorter.interfaces

import com.google.gson.JsonObject

interface Search {

    fun onTitleSearch(result:JsonObject)
    fun onIdSearch(result:JsonObject)
    fun onTParamSearch(result:JsonObject)

}
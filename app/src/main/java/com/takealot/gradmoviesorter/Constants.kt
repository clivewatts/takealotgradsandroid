package com.takealot.gradmoviesorter

//API
const val apiKey:String = "8078a9bb"
const val baseUrl:String = "http://www.omdbapi.com/?apikey=$apiKey"

//Search Types
const val SEARCH_TITLE:String = "$baseUrl&t="
const val SEARCH_ID:String = "$baseUrl&t="
const val SEARCH_TPARAM:String = "$baseUrl&t="
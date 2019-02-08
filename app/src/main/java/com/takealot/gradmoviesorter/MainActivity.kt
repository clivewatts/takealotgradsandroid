package com.takealot.gradmoviesorter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.takealot.gradmoviesorter.api.ApiHelper
import com.takealot.gradmoviesorter.interfaces.Search
import com.takealot.gradmoviesorter.objects.Title

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), Search {

    lateinit var responseCache: JsonObject


    //Setting up our screen and button functionality
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        setUpSearchInput()

        fab.setOnClickListener { view ->
            doSearch()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    private fun setUpSearchInput() {
        fab.isEnabled = false
        search_input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                fab.isEnabled = s!!.isNotEmpty()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
    }

    private fun showLoading() {
        container_layout.visibility = View.GONE
        progress.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        container_layout.visibility = View.VISIBLE
        progress.visibility = View.GONE
    }

    private fun populateInfoList(titleInfo:Title) {
        var stringArray = arrayListOf<String>()
        for (key in responseCache.keySet()) {
            val info = "$key: ${responseCache.get(key)}"
            stringArray.add(info)
        }
        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, stringArray)
        info_list.adapter = adapter
        info_list.invalidate()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun doSearch() {
        showLoading()
        ApiHelper.doTitleSearch(this, SEARCH_TITLE, search_input.editableText.toString())
    }

    //Implement our interface. This is where our search results with end up after the api call complete
    override fun onTitleSearch(result: JsonObject) {
        hideLoading()
        responseCache = result
        var title = Gson().fromJson(result,Title::class.java)
        ApiHelper.loadImageIntoView(this, movie_poster, title.Poster)
        populateInfoList(title)
    }

    override fun onIdSearch(result: JsonObject) {
    }

    override fun onTParamSearch(result: JsonObject) {
    }


}

package com.takealot.gradmoviesorter

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.takealot.gradmoviesorter.api.ApiHelper
import com.takealot.gradmoviesorter.interfaces.Search
import com.takealot.gradmoviesorter.objects.Title

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Search {

    //Setting up our screen and button functionality
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            doSearch()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
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
        ApiHelper.doTitleSearch(this, SEARCH_TITLE, "lord of the rings")
    }

    //Implement our interface. This is where our search results with end up after the api call complete
    override fun onTitleSearch(result: JsonObject) {
        var title = Gson().fromJson(result,Title::class.java)
        Log.d("ddsfs", "fsdfdf")
    }

    override fun onIdSearch(result: JsonObject) {
    }

    override fun onTParamSearch(result: JsonObject) {
    }
}

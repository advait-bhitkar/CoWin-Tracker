package com.silverstudio.cowintracker

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var backButton: ImageView
    private lateinit var appTitle: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_CoWinTracker);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponent()

        supportActionBar?.setDisplayHomeAsUpEnabled(false);
    }


    private fun initComponent(){

        backButton = findViewById(R.id.back)
        backButton.visibility = View.GONE




//        val navController = findNavController(R.id.nav_host_fragment)




    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.home ->
            {

                Toast.makeText(this, "dfgfg", Toast.LENGTH_SHORT).show()
                return false

            }
        }
        return false
    }

}
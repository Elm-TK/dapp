package com.example.dpp

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileWriter


class MainActivity : AppCompatActivity() {

    private var number: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener{
            val intent = Intent(this,SecondActivity::class.java)
            intent.putExtra("number", number)
            startActivity(intent)
        }
        updateNumberText()

        if (savedInstanceState != null) {
            number = savedInstanceState.getInt("number")
            updateNumberText()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        number++
        updateNumberText()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("number", number)
        number++
        updateNumberText()
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)
        number++
        updateNumberText()
    }

    private fun updateNumberText() {

        val numberTextView = findViewById<TextView>(R.id.numberTextView)
        numberTextView.text = number.toString()
    }

    override fun onResume() {
        super.onResume()
        logLifecycle("onResume")
    }

    override fun onPause() {
        super.onPause()
        logLifecycle("onPause")
    }

    override fun onStop() {
        super.onStop()
        logLifecycle("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        logLifecycle("onDestroy")
    }

    private fun logLifecycle(lifecycleEvent: String) {
        val logFile = File(filesDir, "lifecycle_log.txt")
        val writer = FileWriter(logFile, true)
        writer.append("MainActivity - $lifecycleEvent\n")
        writer.close()
    }
}
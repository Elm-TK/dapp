package com.example.dpp

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.io.File
import java.io.FileWriter
import kotlin.math.pow

class SecondActivity : AppCompatActivity() {

    private var number: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        val intent = intent
        number = intent.getIntExtra("number", 0)
        updateNumberText()

        if (savedInstanceState != null) {
            number = savedInstanceState.getInt("number")
            updateNumberText()
        }
    }override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        updateNumberText()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("number", number)
    }

    private fun updateNumberText() {
        val square = number.toDouble().pow(2).toInt()

        val numberTextView = findViewById<TextView>(R.id.numberTextView)
        numberTextView.text = square.toString()
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
        writer.append("SecondActivity - $lifecycleEvent\n")
        writer.close()
    }
}
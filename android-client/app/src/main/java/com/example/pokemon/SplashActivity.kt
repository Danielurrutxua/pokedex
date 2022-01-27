package com.example.pokemon

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        startHeavyProcessing()
    }

    private fun startHeavyProcessing() {
        LongOperation().execute("")
    }

    @SuppressLint("StaticFieldLeak")
    private inner class LongOperation :
        AsyncTask<String?, Void?, String>() {
        override fun doInBackground(vararg params: String?): String {
            //some heavy processing resulting in a Data String
            for (i in 0..4) {
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    Thread.interrupted()
                }
            }
            return "whatever result you have"
        }

        override fun onPostExecute(result: String) {
            val i = Intent(this@SplashActivity, MainActivity::class.java)
            i.putExtra("data", result)
            startActivity(i)
            finish()
        }



    }
}
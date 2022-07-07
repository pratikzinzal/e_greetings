package com.example.e_greetingsapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import com.example.e_greetingsapp.R

class SplashScreenActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
       // requestWindowFeature(Window.FEATURE_NO_TITLE)
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash_screen)





        Handler().postDelayed(Runnable
            {

                startActivity(Intent(this,LoginActivity::class.java))

            },3000)
    }
}
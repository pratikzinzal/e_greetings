package com.example.e_greetingsapp.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.e_greetingsapp.Other.MyUrl
import com.example.e_greetingsapp.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity()
{
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences=getSharedPreferences("user_session", Context.MODE_PRIVATE)



        if (sharedPreferences.getBoolean("user_session", false) && !sharedPreferences.getString("m1", "")!!.isEmpty())
        {
            val i = Intent(this, DashBoardActivity::class.java)
            startActivity(i)
            finish()
        }
        btnLogin.setOnClickListener()
        {
            var number=edtMobile.text.toString()
            var pass=edtPass.text.toString()


            var stringRequest: StringRequest =object :
                StringRequest(Request.Method.POST, MyUrl.login, Response.Listener {response->

                    if(response.trim().equals("0"))
                    {
                        Toast.makeText(applicationContext,"Fail",Toast.LENGTH_LONG).show()
                    }
                    else
                    {

                        Toast.makeText(applicationContext,"Login Success",Toast.LENGTH_LONG).show()
                        var editor:SharedPreferences.Editor=sharedPreferences.edit()
                        editor.putString("m1",number)
                        editor.putString("p1",pass)
                        sharedPreferences.edit().putBoolean("user_session",true).commit();
                        editor.apply()
                        editor.commit()

                        startActivity(Intent(this,DashBoardActivity::class.java))
                    }


            }
                ,
                {

                    Toast.makeText(applicationContext,"No Internet", Toast.LENGTH_LONG).show()

                })

            {
                override fun getParams(): MutableMap<String, String>
                {
                    var hashmap=HashMap<String,String>()
                    hashmap.put("number",number)
                    hashmap.put("password",pass)

                    return hashmap
                }
            }

            var queue: RequestQueue = Volley.newRequestQueue(this)
            queue.add(stringRequest)

        }







        btnNewUser.setOnClickListener()
        {
            startActivity(Intent(this,RegisterActivity::class.java))
        }



    }

    override fun onBackPressed()
    {
        finishAffinity()
    }
}
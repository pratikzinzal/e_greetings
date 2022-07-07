package com.example.e_greetingsapp.Activity

import android.content.Intent
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
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnRegister.setOnClickListener()
        {
            var fname=edtFirstName.text.toString()
            var lanme=edtLastName.text.toString()
            var email=edtEmail.text.toString()
            var phone=edtPhone.text.toString()
            var pass=edtPassword.text.toString()
            var cpass=edtConfirmPassword.text.toString()

            if(pass.equals(cpass))
            {
                var stringRequest:StringRequest=object :StringRequest(Request.Method.POST,MyUrl.register,Response.Listener {

                    Toast.makeText(applicationContext,"Registeration done",Toast.LENGTH_LONG).show()
                    startActivity(Intent(this,VerifyActivity::class.java))
                }
                    ,
                    {

                        Toast.makeText(applicationContext,"No Internet",Toast.LENGTH_LONG).show()

                    })

                {
                    override fun getParams(): MutableMap<String, String>
                    {
                        var hashmap=HashMap<String,String>()
                        hashmap.put("firstname",fname)
                        hashmap.put("lastname",lanme)
                        hashmap.put("email",email)
                        hashmap.put("phone",phone)
                        hashmap.put("password",pass)
                        return hashmap
                    }
                }

                var queue:RequestQueue=Volley.newRequestQueue(this)
                queue.add(stringRequest)

            }

            else
            {
                Toast.makeText(applicationContext,"password and confim password are not same",Toast.LENGTH_LONG).show()
            }
            }

        btnAlreadyAccount.setOnClickListener()
        {
            startActivity(Intent(this,LoginActivity::class.java))
        }



    }


}
package com.example.e_greetingsapp.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.e_greetingsapp.Adapter.Dashboard_Adapter
import com.example.e_greetingsapp.Model.Dashboard_Model
import com.example.e_greetingsapp.Other.MyUrl
import com.example.e_greetingsapp.R
import kotlinx.android.synthetic.main.activity_dash_board.*
import org.json.JSONArray

class DashBoardActivity : AppCompatActivity()
{
    lateinit var list: List<Dashboard_Model>
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        list=ArrayList<Dashboard_Model>()
        sharedPreferences=getSharedPreferences("user_session", Context.MODE_PRIVATE)
        Toast.makeText(applicationContext,"welcome "+sharedPreferences.getString("m1","abcd"),Toast.LENGTH_LONG).show()
        var stringRequest=StringRequest(Request.Method.POST,MyUrl.category_dashboard,{response->

            var jsonArray=JSONArray(response)
            for(i in 0 until jsonArray.length())
            {
                var jsonObject=jsonArray.getJSONObject(i)

                var name=jsonObject.getString("c_name")
                var image=jsonObject.getString("c_image")

                var m=Dashboard_Model()
                m.title_board=name
                m.image_dashboard=image
                (list as ArrayList<Dashboard_Model>).add(m)
            }
            var db=Dashboard_Adapter(applicationContext,list)
            gridview.adapter=db

            gridview.setOnItemClickListener()
            {
                    adapterView: AdapterView<*>, view1: View, i: Int, l: Long ->

                        when(i)
                        {

                            0->
                            {
                                var i2 =Intent(this,CategoryActivity::class.java)
                                i2.putExtra("Key",i)
                                startActivity(i2)
                            }


                            1->
                            {
                                var i2 =Intent(this,CategoryActivity::class.java)
                                i2.putExtra("Key",i)
                                startActivity(i2)

                            }


                            2->
                            {
                                var i2 =Intent(this,CategoryActivity::class.java)
                                i2.putExtra("Key",i)
                                startActivity(i2)

                            }


                            3->
                            {
                                var i2 =Intent(this,CategoryActivity::class.java)
                                i2.putExtra("Key",i)
                                startActivity(i2)

                            }

                        }



            }


        })
        {
                Toast.makeText(applicationContext,"No Internet",Toast.LENGTH_LONG).show()
        }

        var queue:RequestQueue=Volley.newRequestQueue(this)
        queue.add(stringRequest)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.option_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when(item.itemId)
        {
            R.id.logout->
            {
                sharedPreferences.edit().clear().commit()
                startActivity(Intent(this,LoginActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
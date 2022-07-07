package com.example.e_greetingsapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.e_greetingsapp.Adapter.Category_Adapter
import com.example.e_greetingsapp.Model.Category_Model
import com.example.e_greetingsapp.Other.MyUrl
import com.example.e_greetingsapp.R
import kotlinx.android.synthetic.main.activity_category.*
import org.json.JSONArray

class CategoryActivity : AppCompatActivity()
{

    lateinit var list: List<Category_Model>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        list=ArrayList()

        var i =intent
        var id=i.getIntExtra("Key",101)

        when(id)
        {
            0->
            {
                var stringRequest=StringRequest(Request.Method.POST,MyUrl.diwali_category,{ response->

                        var jsonArray=JSONArray(response)
                        for(i in 0 until jsonArray.length())
                        {
                            var jsonObject=jsonArray.getJSONObject(i)
                            var image=jsonObject.getString("image")
                            var m=Category_Model()
                            m.image_category=image
                            (list as ArrayList<Category_Model>).add(m)
                        }
                        var category_adapter=Category_Adapter(applicationContext,list)
                        gridview.adapter=category_adapter


                })
                {
                    Toast.makeText(applicationContext,"No Internet",Toast.LENGTH_LONG).show()
                }

                var queue:RequestQueue=Volley.newRequestQueue(this)
                queue.add(stringRequest)
            }
            1->
            {
                var stringRequest=StringRequest(Request.Method.POST,MyUrl.holi_category,{ response->

                    var jsonArray=JSONArray(response)
                    for(i in 0 until jsonArray.length())
                    {
                        var jsonObject=jsonArray.getJSONObject(i)
                        var image=jsonObject.getString("image")
                        var m=Category_Model()
                        m.image_category=image
                        (list as ArrayList<Category_Model>).add(m)
                    }
                    var category_adapter=Category_Adapter(applicationContext,list)
                    gridview.adapter=category_adapter


                })
                {
                    Toast.makeText(applicationContext,"No Internet",Toast.LENGTH_LONG).show()
                }

                var queue:RequestQueue=Volley.newRequestQueue(this)
                queue.add(stringRequest)
            }
        }



    }
}
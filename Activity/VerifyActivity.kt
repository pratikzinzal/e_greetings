package com.example.e_greetingsapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.e_greetingsapp.R
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class VerifyActivity : AppCompatActivity()
{

    lateinit var edt1:EditText
    lateinit var edt2:EditText
    lateinit var btn1:Button
    lateinit var btn2:Button
    var verificationid=""
    lateinit var mAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify)

        edt1=findViewById(R.id.edtnum)
        edt2=findViewById(R.id.edtotp)
        btn1=findViewById(R.id.btn1)
        btn2=findViewById(R.id.btn2)
        mAuth = FirebaseAuth.getInstance()
        btn1.setOnClickListener()
        {
            if(TextUtils.isEmpty(edt1.text.toString()))
            {
                Toast.makeText(applicationContext,"Please Enter valid Number ",Toast.LENGTH_LONG).show()
            }
            else
            {
                var num=edt1.text.toString()
                sendverificationcode(num)
            }
        }
        btn2.setOnClickListener()
        {
            if(TextUtils.isEmpty(edt2.text.toString()))
            {
                Toast.makeText(applicationContext,"Please Enter valid OTP ",Toast.LENGTH_LONG).show()
            }
            else
            {
                verifyCode(edt2.getText().toString());
            }
        }
    }
     val mCallBack: PhoneAuthProvider.OnVerificationStateChangedCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks()
     {

         override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken)
         {
             super.onCodeSent(p0, p1)
             verificationid=p0
         }
         override fun onVerificationCompleted(p0: PhoneAuthCredential)
         {
                val code=p0.smsCode
             if (code != null)
             {

                 edt2!!.setText(code)

                 verifyCode(code)
             }

         }
         override fun onVerificationFailed(p0: FirebaseException)
         {
            Toast.makeText(applicationContext,""+p0.message,Toast.LENGTH_LONG).show()
         }

     }

    private fun verifyCode(code: String)
    {

        val credential = PhoneAuthProvider.getCredential(verificationid!!, code)
        signInWithCredential(credential)

    }

    private fun signInWithCredential(credential: PhoneAuthCredential)
    {
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener{task->
                if(task.isSuccessful)
                {
                    var i=Intent(this,LoginActivity::class.java)
                    startActivity(i)
                    finish()
                }
                else
                {
                    Toast.makeText(applicationContext,"Error",Toast.LENGTH_LONG).show()
                }



            }
            .addOnFailureListener()
            {
                Toast.makeText(applicationContext,"fail",Toast.LENGTH_LONG).show()
            }
    }

    private fun sendverificationcode(num: String)
    {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(num,60,TimeUnit.SECONDS,this,mCallBack)
    }
}
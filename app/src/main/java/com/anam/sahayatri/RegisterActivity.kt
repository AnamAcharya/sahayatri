package com.anam.sahayatri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import entity.Customer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import repository.CustomerRepo

class RegisterActivity : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var contact: EditText
    private lateinit var password: EditText
    private lateinit var cpassword: EditText
    private lateinit var btnregister: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.hide()
        username = findViewById(R.id.edusername)
        contact = findViewById(R.id.edcontact)

        password = findViewById(R.id.edpassword)
        cpassword = findViewById(R.id.cpassword)

        btnregister = findViewById(R.id.btnregister)

        btnregister.setOnClickListener {

            val username = username.text.toString()
            val contact = contact.text.toString()
            val password = password.text.toString()
            val cpassword = cpassword.text.toString()

            if (password != cpassword) {
                Toast.makeText(
                    this@RegisterActivity,
                    "Password and confirm password did not matched!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val customer = Customer(
                    username = username,
                    phone = contact,
                    password = password
                )
                Log.i("Password check", "Password matched")
                saveSharedPref()
                adduser(customer)
            }
        }
    }
        private fun adduser(customer:Customer) {
            CoroutineScope(Dispatchers.IO).launch {
                val customerRepo = CustomerRepo()
                val getResponse = customerRepo.registerUser(customer)
                if(getResponse.success==true){
                    withContext(Main){
                        Toast.makeText(this@RegisterActivity, "Registration Successful", Toast.LENGTH_SHORT).show()
                        goToLogin()
                    }
                }
            }
        }

    private fun goToLogin(){
        startActivity(Intent(this,LoginActivity::class.java))
    }


    /*----------------------------SAVE SHARED PREFERENCES---------------------------------------------------------*/
    private fun saveSharedPref() {
        val password = password.text.toString()
        val contact = contact.text.toString()
        val sharedPref = getSharedPreferences("LoginPreference",
            MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("phone", contact)
        editor.putString("password", password)

        editor.apply()
    }







}
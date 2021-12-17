package com.anam.sahayatri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import repository.CustomerRepo

class LoginActivity : AppCompatActivity() {

    private var phoneShared: String? = ""
    private var passShared: String? = ""
    private var haveLoginCredential = false

    private lateinit var password: EditText
    private lateinit var phone: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnregister: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        password = findViewById(R.id.edpassword)
        phone = findViewById(R.id.edlcontact)
        btnLogin = findViewById(R.id.btnlogin)
        btnregister = findViewById(R.id.btnregister)
        btnLogin.setOnClickListener {
            val phone = phone.text.toString()
            val password = password.text.toString()
            doLogin(phone = phone, password = password)
        }
        btnregister.setOnClickListener {
            Toast.makeText(this, "Wellcome to Register page", Toast.LENGTH_SHORT).show()
            goToRegister()
        }
    }

    private fun doLogin(phone: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val customerRepo = CustomerRepo()
            val getResponse = customerRepo.checkUser(phone, password)
            if (getResponse.success == true) {
                withContext(Main) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Welcome to dashboard",
                        Toast.LENGTH_SHORT
                    ).show()
                    goToDashboard()
                }
            }
        }

    }

    private fun goToRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    private fun goToDashboard() {
        startActivity(Intent(this, DashboardActivity::class.java))
    }
}
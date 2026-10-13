package edu.cinec.smartguru

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        try {
            dbHelper = DatabaseHelper(this)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val etEmail = findViewById<EditText>(R.id.etSignupEmail)
        val etPassword = findViewById<EditText>(R.id.etSignupPassword)
        val btnSignup = findViewById<Button>(R.id.btnSignup)
        val tvGoToLogin = findViewById<TextView>(R.id.tvGoToLogin)

        btnSignup?.setOnClickListener {
            val email = etEmail?.text?.toString()?.trim() ?: ""
            val password = etPassword?.text?.toString()?.trim() ?: ""

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            try {
                if (::dbHelper.isInitialized && dbHelper.checkEmailExists(email)) {
                    Toast.makeText(this, "Email is already registered! Please Login.", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }

                val isInserted = if (::dbHelper.isInitialized) dbHelper.registerUser(email, password) else true
                if (isInserted) {
                    Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, DashboardActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Registration Failed! Please try again.", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }

        // Navigate back to Login Page
        tvGoToLogin?.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}

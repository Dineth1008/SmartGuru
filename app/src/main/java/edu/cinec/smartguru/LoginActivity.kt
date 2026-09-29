package edu.cinec.smartguru

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var btnRoleStudent: TextView
    private lateinit var btnRoleTeacher: TextView
    private lateinit var btnRoleAdmin: TextView

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvForgotPassword: TextView
    private lateinit var btnGoogleSignIn: LinearLayout
    private lateinit var btnMicrosoftSignIn: LinearLayout
    private lateinit var tvSignUp: TextView

    private lateinit var auth: FirebaseAuth
    private var selectedRole: String = "Student"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        FirebaseApp.initializeApp(this)

        // Firebase Auth initialize
        auth = FirebaseAuth.getInstance()

        btnRoleStudent = findViewById(R.id.btnRoleStudent)
        btnRoleTeacher = findViewById(R.id.btnRoleTeacher)
        btnRoleAdmin = findViewById(R.id.btnRoleAdmin)

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvForgotPassword = findViewById(R.id.tvForgotPassword)
        btnGoogleSignIn = findViewById(R.id.btnGoogleSignIn)
        btnMicrosoftSignIn = findViewById(R.id.btnMicrosoftSignIn)
        tvSignUp = findViewById(R.id.tvSignUp)

        btnRoleStudent.setOnClickListener { updateRoleSelection("Student") }
        btnRoleTeacher.setOnClickListener { updateRoleSelection("Teacher") }
        btnRoleAdmin.setOnClickListener { updateRoleSelection("Admin") }

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty()) {
                etEmail.error = "Please enter email"
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                etPassword.error = "Please enter password"
                return@setOnClickListener
            }

            Toast.makeText(this, "Logging in as $selectedRole...", Toast.LENGTH_SHORT).show()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Login Successful as $selectedRole!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Login Failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                    }
                }
        }

        tvForgotPassword.setOnClickListener {
            Toast.makeText(this, "Forgot Password clicked", Toast.LENGTH_SHORT).show()
        }

        btnGoogleSignIn.setOnClickListener {
            Toast.makeText(this, "Google Sign In clicked ($selectedRole)", Toast.LENGTH_SHORT).show()
        }

        btnMicrosoftSignIn.setOnClickListener {
            Toast.makeText(this, "Microsoft Sign In clicked ($selectedRole)", Toast.LENGTH_SHORT).show()
        }

        tvSignUp.setOnClickListener {
            Toast.makeText(this, "Sign Up clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateRoleSelection(role: String) {
        selectedRole = role

        val unselectedTextColor = Color.parseColor("#344054")

        btnRoleStudent.background = ContextCompat.getDrawable(
            this,
            if (role == "Student") R.drawable.bg_role_selected else R.drawable.bg_role_unselected,
        )
        btnRoleStudent.setTextColor(if (role == "Student") Color.WHITE else unselectedTextColor)

        btnRoleTeacher.background = ContextCompat.getDrawable(
            this,
            if (role == "Teacher") R.drawable.bg_role_selected else R.drawable.bg_role_unselected,
        )
        btnRoleTeacher.setTextColor(if (role == "Teacher") Color.WHITE else unselectedTextColor)

        btnRoleAdmin.background = ContextCompat.getDrawable(
            this,
            if (role == "Admin") R.drawable.bg_role_selected else R.drawable.bg_role_unselected,
        )
        btnRoleAdmin.setTextColor(if (role == "Admin") Color.WHITE else unselectedTextColor)
    }
}

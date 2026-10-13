package edu.cinec.smartguru

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            try {
                FirebaseApp.initializeApp(this)
                val currentUser = FirebaseAuth.getInstance().currentUser

                if (currentUser != null) {
                    startActivity(Intent(this, DashboardActivity::class.java))
                } else {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            } catch (_: Exception) {
                // Firebase Exception Handling - Prevent crash if google-services.json is missing or not configured
                startActivity(Intent(this, LoginActivity::class.java))
            }

            finish()
        }, 3000)
    }
}

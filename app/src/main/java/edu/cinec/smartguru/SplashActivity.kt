package edu.cinec.smartguru

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // 3 sec log next page
        Handler(Looper.getMainLooper()).postDelayed({

            // Firebase login view
            val currentUser = FirebaseAuth.getInstance().currentUser

            if (currentUser != null) {
                // already log go to dashboard
                startActivity(Intent(this, DashboardActivity::class.java))
            } else {
                // signup
                startActivity(Intent(this, LoginActivity::class.java))
            }

            finish() // remove Splash screen

        }, 3000)
    }
}

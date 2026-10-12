package edu.cinec.smartguru

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Header and Bottom Nav Notification Bell Click
        findViewById<FrameLayout>(R.id.btnNotification)?.setOnClickListener {
            startActivity(Intent(this, NotificationActivity::class.java))
        }
        findViewById<LinearLayout>(R.id.navBell)?.setOnClickListener {
            startActivity(Intent(this, NotificationActivity::class.java))
        }

        // Find 4 Quick Action cards from XML
        val cardQuiz = findViewById<View>(R.id.cardQuiz)
        val cardAttendance = findViewById<View>(R.id.cardAttendance)
        val cardPayment = findViewById<View>(R.id.cardPayment)
        val cardResource = findViewById<View>(R.id.cardResource)

        // 1. When MCQ (Quiz) card is clicked
        cardQuiz?.setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java))
        }

        // 2. When Attendance card is clicked
        cardAttendance?.setOnClickListener {
            startActivity(Intent(this, AttendanceActivity::class.java))
        }

        // 3. When Payment card is clicked
        cardPayment?.setOnClickListener {
            startActivity(Intent(this, PaymentActivity::class.java))
        }

        // 4. When Resources card is clicked
        cardResource?.setOnClickListener {
            startActivity(Intent(this, ResourceActivity::class.java))
        }
    }
}

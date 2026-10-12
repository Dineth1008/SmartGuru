package edu.cinec.smartguru

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Header Notification Bell Click
        findViewById<FrameLayout>(R.id.btnNotification)?.setOnClickListener {
            val intent = Intent(this, NotificationActivity::class.java)
            startActivity(intent)
        }

        // Bottom Nav Notification Bell Click
        findViewById<LinearLayout>(R.id.navBell)?.setOnClickListener {
            val intent = Intent(this, NotificationActivity::class.java)
            startActivity(intent)
        }

        // Quick Actions Click Listeners
        findViewById<LinearLayout>(R.id.btnAttendance)?.setOnClickListener {
            val intent = Intent(this, AttendanceActivity::class.java)
            startActivity(intent)
        }

        findViewById<LinearLayout>(R.id.btnPayments)?.setOnClickListener {
            val intent = Intent(this, PaymentActivity::class.java)
            startActivity(intent)
        }

        findViewById<LinearLayout>(R.id.btnQuizzes)?.setOnClickListener {
            Toast.makeText(this, "Quizzes clicked", Toast.LENGTH_SHORT).show()
        }

        findViewById<LinearLayout>(R.id.btnResources)?.setOnClickListener {
            val intent = Intent(this, ResourceActivity::class.java)
            startActivity(intent)
        }
    }
}

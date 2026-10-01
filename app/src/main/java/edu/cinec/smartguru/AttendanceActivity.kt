package edu.cinec.smartguru

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AttendanceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendance)

        findViewById<ImageView>(R.id.btnBack)?.setOnClickListener {
            finish()
        }

        findViewById<LinearLayout>(R.id.btnScanQR)?.setOnClickListener {
            Toast.makeText(this, "Scan QR Code clicked", Toast.LENGTH_SHORT).show()
        }
    }
}

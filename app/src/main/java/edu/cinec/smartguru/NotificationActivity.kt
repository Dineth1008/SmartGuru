package edu.cinec.smartguru

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class NotificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        findViewById<ImageView>(R.id.btnBack)?.setOnClickListener {
            finish()
        }
    }
}

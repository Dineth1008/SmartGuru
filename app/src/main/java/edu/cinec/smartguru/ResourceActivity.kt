package edu.cinec.smartguru

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ResourceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resource)

        findViewById<ImageView>(R.id.btnBack)?.setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.btnDownloadPDF)?.setOnClickListener {
            Toast.makeText(this, "Downloading PDF...", Toast.LENGTH_SHORT).show()
        }
    }
}

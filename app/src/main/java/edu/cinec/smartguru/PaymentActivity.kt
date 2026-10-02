package edu.cinec.smartguru

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        findViewById<ImageView>(R.id.btnBack)?.setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.btnPayOnline)?.setOnClickListener {
            Toast.makeText(this, "Redirecting to PayHere...", Toast.LENGTH_SHORT).show()
        }

        findViewById<LinearLayout>(R.id.boxFileUpload)?.setOnClickListener {
            Toast.makeText(this, "Select File to Upload", Toast.LENGTH_SHORT).show()
        }

        findViewById<LinearLayout>(R.id.btnNoUpload)?.setOnClickListener {
            Toast.makeText(this, "No Upload selected", Toast.LENGTH_SHORT).show()
        }
    }
}

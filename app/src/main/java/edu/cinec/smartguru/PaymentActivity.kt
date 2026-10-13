package edu.cinec.smartguru

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class PaymentActivity : AppCompatActivity() {

    private lateinit var tvFileName: TextView
    private var selectedReceiptUri: Uri? = null

    // Register ActivityResultLauncher to pick Image or PDF Receipt
    private val selectReceiptLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
                selectedReceiptUri = uri
                val name = uri.lastPathSegment ?: "Bank_Receipt.jpg"
                tvFileName.text = "Selected: $name"
                Toast.makeText(this, "Receipt selected successfully!", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        tvFileName = findViewById(R.id.tvFileName)

        // Back button
        findViewById<ImageView>(R.id.btnBack)?.setOnClickListener {
            finish()
        }

        // Pay Online button -> PayHere Portal redirect
        findViewById<Button>(R.id.btnPayOnline)?.setOnClickListener {
            val payHereUrl = "https://www.payhere.lk"
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(payHereUrl))
                startActivity(intent)
            } catch (_: Exception) {
                Toast.makeText(this, "Opening PayHere Portal...", Toast.LENGTH_SHORT).show()
            }
        }

        // File Upload Dropzone -> Pick Image/PDF
        findViewById<LinearLayout>(R.id.boxFileUpload)?.setOnClickListener {
            selectReceiptLauncher.launch("*/*")
        }

        // No Upload / Reset Button
        findViewById<LinearLayout>(R.id.btnNoUpload)?.setOnClickListener {
            if (selectedReceiptUri != null) {
                selectedReceiptUri = null
                tvFileName.text = getString(R.string.file_upload)
                Toast.makeText(this, "Receipt selection cleared.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Proceeding without receipt upload.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

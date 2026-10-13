package edu.cinec.smartguru

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class AttendanceActivity : AppCompatActivity() {

    private lateinit var tabPresent: LinearLayout
    private lateinit var tabAbsent: LinearLayout
    private lateinit var tvTabPresent: TextView
    private lateinit var tvTabAbsent: TextView

    private lateinit var itemClass1: View
    private lateinit var itemClass2: View
    private lateinit var itemClass3: View
    private lateinit var itemClass4: View

    private lateinit var tvCalendarHeader: TextView
    private var currentYear = 2026

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendance)

        findViewById<ImageView>(R.id.btnBack)?.setOnClickListener {
            finish()
        }

        tabPresent = findViewById(R.id.tabPresent)
        tabAbsent = findViewById(R.id.tabAbsent)
        tvTabPresent = findViewById(R.id.tvTabPresent)
        tvTabAbsent = findViewById(R.id.tvTabAbsent)

        itemClass1 = findViewById(R.id.itemClass1)
        itemClass2 = findViewById(R.id.itemClass2)
        itemClass3 = findViewById(R.id.itemClass3)
        itemClass4 = findViewById(R.id.itemClass4)

        tvCalendarHeader = findViewById(R.id.tvCalendarHeader)
        tvCalendarHeader.text = "Calendar $currentYear"

        // Month Navigation
        findViewById<ImageView>(R.id.btnPrevMonth)?.setOnClickListener {
            currentYear--
            tvCalendarHeader.text = "Calendar $currentYear"
            Toast.makeText(this, "Year: $currentYear", Toast.LENGTH_SHORT).show()
        }

        findViewById<ImageView>(R.id.btnNextMonth)?.setOnClickListener {
            currentYear++
            tvCalendarHeader.text = "Calendar $currentYear"
            Toast.makeText(this, "Year: $currentYear", Toast.LENGTH_SHORT).show()
        }

        // Selected Day click
        findViewById<TextView>(R.id.tvDay16)?.setOnClickListener {
            Toast.makeText(this, "Selected Date: 16 $currentYear", Toast.LENGTH_SHORT).show()
        }

        // Present Tab Click
        tabPresent.setOnClickListener {
            selectFilterTab(showPresent = true)
        }

        // Absent Tab Click
        tabAbsent.setOnClickListener {
            selectFilterTab(showPresent = false)
        }

        // Scan QR Button Click
        findViewById<LinearLayout>(R.id.btnScanQR)?.setOnClickListener {
            Toast.makeText(this, "Scan QR Code clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun selectFilterTab(showPresent: Boolean) {
        if (showPresent) {
            tabPresent.background = ContextCompat.getDrawable(this, R.drawable.bg_role_unselected)
            tvTabPresent.setTextColor(Color.parseColor("#12B76A"))

            tabAbsent.background = null
            tvTabAbsent.setTextColor(Color.parseColor("#667085"))

            itemClass1.visibility = View.VISIBLE
            itemClass2.visibility = View.VISIBLE
            itemClass3.visibility = View.GONE
            itemClass4.visibility = View.GONE

            Toast.makeText(this, "Present Classes", Toast.LENGTH_SHORT).show()
        } else {
            tabAbsent.background = ContextCompat.getDrawable(this, R.drawable.bg_role_unselected)
            tvTabAbsent.setTextColor(Color.parseColor("#D92D20"))

            tabPresent.background = null
            tvTabPresent.setTextColor(Color.parseColor("#667085"))

            itemClass1.visibility = View.GONE
            itemClass2.visibility = View.GONE
            itemClass3.visibility = View.VISIBLE
            itemClass4.visibility = View.VISIBLE

            Toast.makeText(this, "Absent Classes", Toast.LENGTH_SHORT).show()
        }
    }
}

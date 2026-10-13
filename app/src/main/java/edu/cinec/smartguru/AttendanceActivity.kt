package edu.cinec.smartguru

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

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
    private lateinit var calendarGrid: GridLayout

    private val calendar = Calendar.getInstance()
    private val todayCalendar = Calendar.getInstance()
    private var selectedDay = todayCalendar.get(Calendar.DAY_OF_MONTH)

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
        calendarGrid = findViewById(R.id.calendarGrid)

        updateCalendarDisplay()

        // Month Navigation
        findViewById<ImageView>(R.id.btnPrevMonth)?.setOnClickListener {
            calendar.add(Calendar.MONTH, -1)
            updateCalendarDisplay()
        }

        findViewById<ImageView>(R.id.btnNextMonth)?.setOnClickListener {
            calendar.add(Calendar.MONTH, 1)
            updateCalendarDisplay()
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

    private fun updateCalendarDisplay() {
        val monthYearFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
        tvCalendarHeader.text = monthYearFormat.format(calendar.time)

        calendarGrid.removeAllViews()

        val tempCalendar = calendar.clone() as Calendar
        tempCalendar.set(Calendar.DAY_OF_MONTH, 1)

        val firstDayOfWeek = tempCalendar.get(Calendar.DAY_OF_WEEK) - 1 // 0-based Sunday index
        val daysInMonth = tempCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        tempCalendar.add(Calendar.MONTH, -1)
        val prevMonthDays = tempCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        val isCurrentMonthAndYear = (calendar.get(Calendar.YEAR) == todayCalendar.get(Calendar.YEAR)
                && calendar.get(Calendar.MONTH) == todayCalendar.get(Calendar.MONTH))

        val totalCells = 35 // 5 rows x 7 columns

        val screenWidth = resources.displayMetrics.widthPixels - (40 * resources.displayMetrics.density).toInt()
        val cellSize = screenWidth / 7

        for (i in 0 until totalCells) {
            val frameLayout = FrameLayout(this).apply {
                layoutParams = GridLayout.LayoutParams().apply {
                    width = cellSize
                    height = (36 * resources.displayMetrics.density).toInt()
                }
            }

            val textView = TextView(this).apply {
                gravity = Gravity.CENTER
                textSize = 14f
            }

            if (i < firstDayOfWeek) {
                // Previous month trailing days
                val day = prevMonthDays - firstDayOfWeek + i + 1
                textView.text = day.toString()
                textView.setTextColor(Color.parseColor("#D0D5DD"))
            } else if (i < firstDayOfWeek + daysInMonth) {
                // Current month days
                val day = i - firstDayOfWeek + 1
                textView.text = day.toString()

                val isToday = isCurrentMonthAndYear && (day == todayCalendar.get(Calendar.DAY_OF_MONTH))
                val isSelected = (day == selectedDay)

                if (isToday || isSelected) {
                    val selectedView = TextView(this).apply {
                        layoutParams = FrameLayout.LayoutParams(
                            (32 * resources.displayMetrics.density).toInt(),
                            (32 * resources.displayMetrics.density).toInt()
                        ).apply {
                            gravity = Gravity.CENTER
                        }
                        background = ContextCompat.getDrawable(this@AttendanceActivity, R.drawable.bg_calendar_selected_day)
                        text = day.toString()
                        setTextColor(Color.WHITE)
                        gravity = Gravity.CENTER
                        textSize = 14f
                        setTypeface(null, Typeface.BOLD)
                    }
                    frameLayout.addView(selectedView)
                } else {
                    textView.setTextColor(Color.parseColor("#101828"))
                    frameLayout.addView(textView)
                }

                frameLayout.setOnClickListener {
                    selectedDay = day
                    updateCalendarDisplay()
                    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                    val clickCal = calendar.clone() as Calendar
                    clickCal.set(Calendar.DAY_OF_MONTH, day)
                    Toast.makeText(this, "Selected: ${dateFormat.format(clickCal.time)}", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Next month leading days
                val day = i - (firstDayOfWeek + daysInMonth) + 1
                textView.text = day.toString()
                textView.setTextColor(Color.parseColor("#D0D5DD"))
            }

            if (frameLayout.childCount == 0) {
                frameLayout.addView(textView)
            }

            calendarGrid.addView(frameLayout)
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

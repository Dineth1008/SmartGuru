package edu.cinec.smartguru

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
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

        val samplePdfUrl = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"

        // Main Download PDF button
        findViewById<Button>(R.id.btnDownloadPDF)?.setOnClickListener {
            downloadPdf("SmartGuru_Lesson.pdf", samplePdfUrl)
        }

        // Lesson Cards PDF Downloads
        findViewById<ImageView>(R.id.btnDownloadLesson1)?.setOnClickListener {
            downloadPdf("Lesson_1_Notes.pdf", samplePdfUrl)
        }

        findViewById<ImageView>(R.id.btnDownloadLesson2)?.setOnClickListener {
            downloadPdf("Lesson_2_Notes.pdf", samplePdfUrl)
        }

        findViewById<ImageView>(R.id.btnDownloadLesson3)?.setOnClickListener {
            downloadPdf("Lesson_3_Notes.pdf", samplePdfUrl)
        }

        findViewById<ImageView>(R.id.btnDownloadLesson4)?.setOnClickListener {
            downloadPdf("Lesson_4_Notes.pdf", samplePdfUrl)
        }

        findViewById<ImageView>(R.id.btnDownloadLesson5)?.setOnClickListener {
            downloadPdf("Lesson_5_Notes.pdf", samplePdfUrl)
        }

        findViewById<ImageView>(R.id.btnDownloadLesson6)?.setOnClickListener {
            downloadPdf("Lesson_6_Notes.pdf", samplePdfUrl)
        }
    }

    private fun downloadPdf(fileName: String, url: String) {
        try {
            val request = DownloadManager.Request(Uri.parse(url)).apply {
                setTitle(fileName)
                setDescription("Downloading $fileName...")
                setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
                setAllowedOverMetered(true)
                setAllowedOverRoaming(true)
            }

            val downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            downloadManager.enqueue(request)

            Toast.makeText(this, "Downloading $fileName to Downloads folder...", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Download failed: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}

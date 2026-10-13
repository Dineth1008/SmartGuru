package edu.cinec.smartguru

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "SmartGuru.db"
        private const val DATABASE_VERSION = 2

        // Users Table
        private const val TABLE_USERS = "users"
        private const val COLUMN_USER_ID = "id"
        private const val COLUMN_USER_EMAIL = "email"
        private const val COLUMN_USER_PASSWORD = "password"

        // Attendance Table
        private const val TABLE_ATTENDANCE = "attendance"
        private const val COLUMN_ATT_ID = "id"
        private const val COLUMN_ATT_EMAIL = "user_email"
        private const val COLUMN_ATT_CLASS = "class_name"
        private const val COLUMN_ATT_DATE = "date"
        private const val COLUMN_ATT_STATUS = "status"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createUsersTable = ("CREATE TABLE IF NOT EXISTS " + TABLE_USERS + " ("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USER_EMAIL + " TEXT UNIQUE, "
                + COLUMN_USER_PASSWORD + " TEXT)")

        val createAttendanceTable = ("CREATE TABLE IF NOT EXISTS " + TABLE_ATTENDANCE + " ("
                + COLUMN_ATT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_ATT_EMAIL + " TEXT, "
                + COLUMN_ATT_CLASS + " TEXT, "
                + COLUMN_ATT_DATE + " TEXT, "
                + COLUMN_ATT_STATUS + " TEXT)")

        db.execSQL(createUsersTable)
        db.execSQL(createAttendanceTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ATTENDANCE")
        onCreate(db)
    }

    // Register user with SQLite
    fun registerUser(email: String, password: String): Boolean {
        return try {
            val db = this.writableDatabase
            val values = ContentValues().apply {
                put(COLUMN_USER_EMAIL, email)
                put(COLUMN_USER_PASSWORD, password)
            }
            val result = db.insert(TABLE_USERS, null, values)
            db.close()
            result != -1L
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    // Check if user exists with matching email & password (for Login)
    fun checkUser(email: String, password: String): Boolean {
        return try {
            val db = this.readableDatabase
            val columns = arrayOf(COLUMN_USER_ID)
            val selection = "$COLUMN_USER_EMAIL = ? AND $COLUMN_USER_PASSWORD = ?"
            val selectionArgs = arrayOf(email, password)

            val cursor = db.query(
                TABLE_USERS,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
            )
            val count = cursor.count
            cursor.close()
            db.close()
            count > 0
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    // Check if email already registered
    fun checkEmailExists(email: String): Boolean {
        return try {
            val db = this.readableDatabase
            val columns = arrayOf(COLUMN_USER_ID)
            val selection = "$COLUMN_USER_EMAIL = ?"
            val selectionArgs = arrayOf(email)

            val cursor = db.query(
                TABLE_USERS,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
            )
            val count = cursor.count
            cursor.close()
            db.close()
            count > 0
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}

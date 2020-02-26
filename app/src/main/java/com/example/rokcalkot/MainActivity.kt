package com.example.rokcalkot

import android.content.Intent
import android.app.Activity
import android.os.Build
import android.provider.Settings
import android.view.View
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.net.Uri
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Bitmap


class MainActivity : AppCompatActivity() {

    val requestCode = 201

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //-------------------------- SERVICE BUTTON BTN -------------------------
        /*val btnS = findViewById<Button>(R.id.btnService)

        btnS.setOnClickListener(View.OnClickListener {
            Toast.makeText(this@MainActivity, "Clickble", Toast.LENGTH_LONG).show()
        })
*/
        //-------------------------- LAUNCH BUTTON BTN ---------------------------
        val btnL = findViewById<Button>(R.id.btnShow)
        //btnShow?.setOnClickListener { Toast.makeText(this@MainActivity, "Bannaa", Toast.LENGTH_LONG).show() }
        btnL.setOnClickListener(View.OnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:$packageName")
                )

                /*val intent2 = Intent(
                    Settings.ACTION_INTERNAL_STORAGE_SETTINGS,
                    Uri.parse("package:$packageName")
                )*/
                startActivityForResult(intent, requestCode)
                //startActivityForResult(intent2,requestCode1)
            } else {
                startService(Intent(this, MyFloatingWindowService::class.java))


                finish()
            }
            Toast.makeText(this@MainActivity, "Bannaa", Toast.LENGTH_LONG).show()
            })

        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if (resultCode == Activity.RESULT_OK) {
                startService(Intent(this, MyFloatingWindowService::class.java))
                finish()
            }
        }
//--------------- TAKE SCREENSHOT ----------------
        fun takeScreenshotOfView(view: View, height: Int, width: Int): Bitmap {
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            val bgDrawable = view.background
            if (bgDrawable != null) {
                bgDrawable.draw(canvas)
            } else {
                canvas.drawColor(Color.WHITE)
            }
            view.draw(canvas)
            return bitmap
        }
    }
}


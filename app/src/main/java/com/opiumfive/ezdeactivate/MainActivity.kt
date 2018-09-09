package com.opiumfive.ezdeactivate

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        // check not earlier than in onResume
        AppActiveChecker.checkIfActive {
            alert("Active = $it", "Checked if the app is enabled") {
                yesButton { it.dismiss() }
            }.show()
        }
    }
}

package com.exsilicium.wearesv

import android.os.Bundle
import android.support.wearable.activity.WearableActivityDelegate
import androidx.fragment.app.FragmentActivity

class MainActivity : FragmentActivity() {
    private val wearableActivityDelegate = WearableActivityDelegate(
        object : WearableActivityDelegate.AmbientCallback {
            override fun onExitAmbient() {}
            override fun onUpdateAmbient() {}
            override fun onEnterAmbient(ambientDetails: Bundle?) {}
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wearableActivityDelegate.onCreate(this)
        wearableActivityDelegate.setAmbientEnabled()
    }

    override fun onResume() {
        super.onResume()
        wearableActivityDelegate.onResume()
    }

    override fun onPause() {
        wearableActivityDelegate.onPause()
        super.onPause()
    }

    override fun onStop() {
        wearableActivityDelegate.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        wearableActivityDelegate.onDestroy()
        super.onDestroy()
    }
}

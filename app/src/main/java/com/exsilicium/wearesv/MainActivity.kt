package com.exsilicium.wearesv

import android.os.Bundle
import android.support.wearable.activity.WearableActivityDelegate
import androidx.annotation.CallSuper
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

    @CallSuper
    override fun onResume() {
        super.onResume()
        wearableActivityDelegate.onResume()
    }

    @CallSuper
    override fun onPause() {
        wearableActivityDelegate.onPause()
        super.onPause()
    }

    @CallSuper
    override fun onStop() {
        wearableActivityDelegate.onStop()
        super.onStop()
    }

    @CallSuper
    override fun onDestroy() {
        wearableActivityDelegate.onDestroy()
        super.onDestroy()
    }
}

package com.exsilicium.wearesv.list

import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.annotation.FloatRange
import androidx.recyclerview.widget.RecyclerView
import androidx.wear.widget.WearableLinearLayoutManager
import kotlin.math.abs

/** How much should we scale the item at most. */
private const val MAX_PROGRESS = 0.8f

internal class CustomScrollingLayoutCallback(
    @FloatRange(from = 0.0, to = 1.0) private val scaleFactor: Float = MAX_PROGRESS
) : WearableLinearLayoutManager.LayoutCallback() {
    private var initialTextSize = 0.0f
    private var progressToCenter = 0f

    override fun onLayoutFinished(child: View, parent: RecyclerView) {
        if (initialTextSize == 0.0f) {
            initialTextSize = (child as TextView).textSize
        }
        child.apply {
            // Figure out % progress from top to bottom
            val centerOffset = height.toFloat() / 2.0f / parent.height.toFloat()
            val yRelativeToCenterOffset = y / parent.height + centerOffset

            // Normalize for center & adjust to the maximum scale
            progressToCenter = abs(0.5f - yRelativeToCenterOffset).coerceAtMost(scaleFactor)

            (this as TextView).setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                initialTextSize * (1 - progressToCenter)
            )

            alpha = 1 - progressToCenter

            requestLayout()
        }
    }
}

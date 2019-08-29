package com.exsilicium.wearesv.list.number

import androidx.navigation.findNavController
import androidx.wear.widget.SwipeDismissFrameLayout

class SwipeToPopBackStackCallback(
    private val onDismissed: () -> Unit = {}
) : SwipeDismissFrameLayout.Callback() {
    override fun onSwipeStarted(layout: SwipeDismissFrameLayout) {}
    override fun onSwipeCanceled(layout: SwipeDismissFrameLayout) {}
    override fun onDismissed(layout: SwipeDismissFrameLayout) {
        onDismissed()
        layout.findNavController().popBackStack()
    }
}

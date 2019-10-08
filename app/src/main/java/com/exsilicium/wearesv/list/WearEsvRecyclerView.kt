package com.exsilicium.wearesv.list

import android.content.Context
import android.util.AttributeSet
import androidx.wear.widget.WearableLinearLayoutManager
import androidx.wear.widget.WearableRecyclerView

class WearEsvRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : WearableRecyclerView(context, attrs, defStyle) {
    init {
        requestFocus()
        isEdgeItemsCenteringEnabled = true
        layoutManager = WearableLinearLayoutManager(context, CustomScrollingLayoutCallback())
    }
}

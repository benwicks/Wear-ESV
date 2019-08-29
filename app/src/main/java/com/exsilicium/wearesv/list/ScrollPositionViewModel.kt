package com.exsilicium.wearesv.list

import androidx.lifecycle.ViewModel

class ScrollPositionViewModel : ViewModel() {
    var scrollState = UNSET

    fun shouldRestore() = scrollState != UNSET

    companion object {
        private const val UNSET = -1
    }
}

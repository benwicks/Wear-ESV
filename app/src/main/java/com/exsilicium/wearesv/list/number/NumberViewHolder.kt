package com.exsilicium.wearesv.list.number

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NumberViewHolder(
    itemView: View,
    private val onClickListener: (Int) -> Unit
) : RecyclerView.ViewHolder(itemView) {
    fun bind(position: Int) {
        (itemView as TextView).apply {
            val number = position + 1
            text = number.toString()

            setOnClickListener { onClickListener(number) }
        }
    }
}

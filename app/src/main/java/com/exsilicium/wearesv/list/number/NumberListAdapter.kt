package com.exsilicium.wearesv.list.number

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exsilicium.wearesv.R

class NumberListAdapter(
    private val itemCount: Int,
    private val onClickListener: (Int) -> Unit
) : RecyclerView.Adapter<NumberViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NumberViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item_number, parent, false),
        onClickListener
    )

    override fun getItemCount() = itemCount

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        holder.bind(position)
    }
}

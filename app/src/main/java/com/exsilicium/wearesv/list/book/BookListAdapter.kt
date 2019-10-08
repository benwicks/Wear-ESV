package com.exsilicium.wearesv.list.book

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exsilicium.scripture.shared.model.Book
import com.exsilicium.wearesv.R

class BookListAdapter : RecyclerView.Adapter<BookViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BookViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item_book, parent, false)
    )

    override fun getItemCount() = Book.values().size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(position)
    }
}

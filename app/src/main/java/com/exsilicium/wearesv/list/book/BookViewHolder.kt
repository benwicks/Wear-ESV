package com.exsilicium.wearesv.list.book

import android.view.View
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.exsilicium.scripture.shared.model.Book
import com.exsilicium.wearesv.list.book.BookListFragmentDirections.Companion.actionBookListFragmentToChapterListFragment

class BookViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    fun bind(position: Int) {
        (itemView as TextView).apply {
            val book = Book.values()[position]
            text = book.title

            setOnClickListener {
                findNavController().navigate(actionBookListFragmentToChapterListFragment(book))
            }
        }
    }
}

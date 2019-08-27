package com.exsilicium.wearesv

import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.wear.widget.WearableLinearLayoutManager
import androidx.wear.widget.WearableRecyclerView
import com.exsilicium.scripture.shared.model.Book
import com.exsilicium.wearesv.book.BookViewHolder
import com.exsilicium.wearesv.list.CustomScrollingLayoutCallback

class MainActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Enables Always-on.
        setAmbientEnabled()

        findViewById<WearableRecyclerView>(R.id.recycler_view).apply {
            isEdgeItemsCenteringEnabled = true

            layoutManager = WearableLinearLayoutManager(
                this@MainActivity,
                CustomScrollingLayoutCallback()
            )

            adapter = object : RecyclerView.Adapter<BookViewHolder>() {
                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
                    return BookViewHolder(
                        LayoutInflater.from(parent.context)
                            .inflate(R.layout.book_list_item, parent, false)
                    )
                }

                override fun getItemCount() = Book.values().size

                override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
                    (holder.itemView as TextView).apply {
                        text = Book.values()[position].title

                        setOnClickListener {
                            // TODO Navigate/Push chapter list
                        }
                    }
                }
            }
        }
    }
}

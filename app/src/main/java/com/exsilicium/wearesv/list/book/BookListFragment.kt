package com.exsilicium.wearesv.list.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exsilicium.wearesv.R
import com.exsilicium.wearesv.list.ScrollPositionViewModel

class BookListFragment : Fragment() {
    private val viewModel by navGraphViewModels<ScrollPositionViewModel>(R.id.nav_graph)

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_book_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO Use new view binding
        recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view).apply {
            adapter = BookListAdapter()
            if (viewModel.shouldRestore()) {
                post {
                    (layoutManager as LinearLayoutManager).scrollToPosition(viewModel.scrollState)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.scrollState = (recyclerView.layoutManager as LinearLayoutManager)
            .findFirstVisibleItemPosition()
    }
}

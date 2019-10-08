package com.exsilicium.wearesv.list.verse

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.wear.widget.SwipeDismissFrameLayout
import com.exsilicium.wearesv.R
import com.exsilicium.wearesv.list.ScrollPositionViewModel
import com.exsilicium.wearesv.list.number.NumberListAdapter
import com.exsilicium.wearesv.list.number.SwipeToPopBackStackCallback
import com.exsilicium.wearesv.list.verse.VerseListFragmentDirections.Companion.actionVerseListFragmentToVerseFragment

class VerseListFragment : Fragment() {
    private val args: VerseListFragmentArgs by navArgs()
    private val viewModel by viewModels<ScrollPositionViewModel>()

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = SwipeDismissFrameLayout(activity).apply {
        inflater.inflate(R.layout.fragment_chapter_or_verse_list, container, false).also(::addView)
        addCallback(SwipeToPopBackStackCallback())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val book = args.book
        val chapter = args.chapter

        view.findViewById<TextView>(R.id.tv_select_label_header).setText(R.string.select_verse)
        @SuppressLint("SetTextI18n")
        view.findViewById<TextView>(R.id.tv_selection_header).text = "${book.title} $chapter"

        recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view).apply {
            adapter = NumberListAdapter(book.versesInChapter(chapter)) {
                findNavController().navigate(
                    actionVerseListFragmentToVerseFragment(book, chapter, it)
                )
            }
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

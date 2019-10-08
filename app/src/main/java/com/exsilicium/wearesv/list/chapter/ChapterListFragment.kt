package com.exsilicium.wearesv.list.chapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.wear.widget.SwipeDismissFrameLayout
import com.exsilicium.wearesv.R
import com.exsilicium.wearesv.databinding.FragmentChapterOrVerseListBinding
import com.exsilicium.wearesv.list.ScrollPositionViewModel
import com.exsilicium.wearesv.list.chapter.ChapterListFragmentDirections.Companion.actionChapterListFragmentToVerseListFragment
import com.exsilicium.wearesv.list.number.NumberListAdapter
import com.exsilicium.wearesv.list.number.SwipeToPopBackStackCallback

class ChapterListFragment : Fragment() {
    private val args: ChapterListFragmentArgs by navArgs()
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

        val binding = FragmentChapterOrVerseListBinding.bind(view)
        binding.headerLabel.setText(R.string.select_chapter)
        binding.selectionHeader.text = book.title

        recyclerView = binding.recyclerView.apply {
            adapter = NumberListAdapter(book.chapterCount) {
                findNavController().navigate(actionChapterListFragmentToVerseListFragment(book, it))
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

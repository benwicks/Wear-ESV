package com.exsilicium.wearesv.scripture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.wear.widget.SwipeDismissFrameLayout
import com.exsilicium.scripture.shared.model.ScriptureReference
import com.exsilicium.scripture.shared.model.Verse
import com.exsilicium.wearesv.R
import com.exsilicium.wearesv.list.number.SwipeToPopBackStackCallback

class ScriptureFragment : Fragment() {
    private val args: ScriptureFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = SwipeDismissFrameLayout(activity).apply {
        inflater.inflate(R.layout.fragment_scripture, container, false).also(::addView)
        addCallback(SwipeToPopBackStackCallback())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.tv_verse_reference).text = ScriptureReference(
            args.book,
            Verse(args.chapter, args.verse)
        ).toString()
    }
}

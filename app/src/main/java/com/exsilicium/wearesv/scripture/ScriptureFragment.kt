package com.exsilicium.wearesv.scripture

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import android.widget.ViewAnimator
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.wear.widget.SwipeDismissFrameLayout
import com.exsilicium.scripture.shared.model.ScriptureReference
import com.exsilicium.scripture.shared.model.Verse
import com.exsilicium.wearesv.R
import com.exsilicium.wearesv.list.number.SwipeToPopBackStackCallback
import com.exsilicium.wearesv.scripture.network.RetrofitFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

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

    /* TODO Clean up. See:
            - https://proandroiddev.com/suspend-what-youre-doing-retrofit-has-now-coroutines-support-c65bd09ba067
            - https://android.jlelse.eu/kotlin-coroutines-and-retrofit-e0702d0b8e8f
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val scriptureReference = ScriptureReference(
            args.book,
            Verse(args.chapter, args.verse)
        )
        view.findViewById<TextView>(R.id.tv_verse_reference).text = scriptureReference.toString()

        val viewFlipper = view.findViewById<ViewAnimator>(R.id.view_flipper)

        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitFactory.makePassageService().getPassage(scriptureReference)
            withContext(Dispatchers.Main) {
                try {
                    val passageTextView = view.findViewById<TextView>(R.id.tv_passage)
                    if (response.isSuccessful) {
                        passageTextView.text = response.body()?.passage()
                        passageTextView.setTextColor(Color.WHITE)
                    } else {
                        @SuppressLint("SetTextI18n")
                        passageTextView.text = "Error: ${response.code()}"
                        passageTextView.setTextColor(Color.RED)
                    }
                    viewFlipper.displayedChild = 1
                } catch (e: HttpException) {
                    Toast.makeText(context, "Exception ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

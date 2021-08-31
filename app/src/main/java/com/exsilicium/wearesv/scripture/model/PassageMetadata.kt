package com.exsilicium.wearesv.scripture.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class PassageMetadata(
    @Json(name = "canonical") val canonical: String,
    @Json(name = "chapter_start") val chapterStart: List<Int>,
    @Json(name = "chapter_end") val chapterEnd: List<Int>,
    @Json(name = "prev_verse") val previousVerse: Int?,
    @Json(name = "next_verse") val nextVerse: Int?,
    @Json(name = "prev_chapter") val previousChapter: List<Int>?,
    @Json(name = "next_chapter") val nextChapter: List<Int>?
)

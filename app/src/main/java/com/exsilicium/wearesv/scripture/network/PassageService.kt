package com.exsilicium.wearesv.scripture.network

import com.exsilicium.scripture.shared.model.ScriptureReference
import com.exsilicium.wearesv.scripture.model.PassageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// TODO Share with [Scripture Memory](https://github.com/Ex-Silicium/ScriptureMemory) project
interface PassageService {

    @GET("passage/text/")
    @Suppress("LongParameterList")
    suspend fun getPassage(
        @Query("q") reference: ScriptureReference,
        @Query("include-passage-references") includeReferences: Boolean = false,
        @Query("include-first-verse-numbers") includeFirstVerseNumbers: Boolean = false,
        @Query("include-verse-numbers") includeVerseNumbers: Boolean = false,
        @Query("include-footnotes") includeFootnotes: Boolean = false,
        @Query("include-footnote-body") includeFootnoteBody: Boolean = false,
        @Query("include-headings") includeHeadings: Boolean = false,
        @Query("include-selahs") includeSelahs: Boolean = false
    ): Response<PassageResponse>
}

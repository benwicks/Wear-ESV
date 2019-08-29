package com.exsilicium.wearesv.scripture.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PassageResponse internal constructor(
    @Json(name = "query") val query: String,
    @Json(name = "canonical") val canonical: String,
    @Json(name = "parsed") val parsed: Set<IntArray>,
    @Json(name = "passage_meta") internal val passageMetadata: List<PassageMetadata>,
    @Json(name = "passages") internal val passages: List<String>
) {
    fun passage() = passages[0].trim()
}

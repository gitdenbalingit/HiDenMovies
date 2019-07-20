package com.hiden.movies.data.entity

import com.google.gson.annotations.SerializedName

data class PageResponse<Data>(
        @SerializedName("count")
        val count: Int,
        @SerializedName("next")
        val next: String? = null,
        @SerializedName("previous")
        val previous: String? = null,
        @SerializedName("results")
        val results: List<Data>
)
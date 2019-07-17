package com.hiden.movies.data.entity

import com.google.gson.annotations.SerializedName


data class SearchResponse(
        @SerializedName("statuses") val statuses: List<SearchStatusResponse>,
        @SerializedName("search_metadata") val search_metadata: SearchMetaDataResponse
)
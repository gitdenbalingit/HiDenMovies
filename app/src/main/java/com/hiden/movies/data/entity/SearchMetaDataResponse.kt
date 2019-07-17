package com.hiden.movies.data.entity

import com.google.gson.annotations.SerializedName

data class SearchMetaDataResponse (

        @SerializedName("completed_in") val completed_in : Double,
        @SerializedName("max_id") val max_id : Long,
        @SerializedName("max_id_str") val max_id_str : String,
        @SerializedName("next_results") val next_results : String,
        @SerializedName("query") val query : String,
        @SerializedName("refresh_url") val refresh_url : String,
        @SerializedName("count") val count : Int,
        @SerializedName("since_id") val since_id : Int,
        @SerializedName("since_id_str") val since_id_str : Int
)
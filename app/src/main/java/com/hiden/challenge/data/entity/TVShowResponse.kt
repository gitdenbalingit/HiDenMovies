package com.hiden.challenge.data.entity

import com.google.gson.annotations.SerializedName

data class TVShowResponse(
    @SerializedName("original_name")
    val originalName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("first_air_date")
    val firstAirDate: String?,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("origin_country")
    val originCountry: List<String>

)
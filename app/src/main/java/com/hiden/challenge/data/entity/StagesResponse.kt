package com.hiden.challenge.data.entity

import com.google.gson.annotations.SerializedName

data class StagesResponse(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String ,
        @SerializedName("is_active")
        val is_active: Boolean = false
)
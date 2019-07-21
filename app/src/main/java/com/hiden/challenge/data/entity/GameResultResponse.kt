package com.hiden.challenge.data.entity


import com.google.gson.annotations.SerializedName

data class GameResultResponse(
        @SerializedName("id")
        val id: Int,
        @SerializedName("image")
        val image: String ,
        @SerializedName("name")
        val name: String ,
        @SerializedName("package_id")
        val packageId: String? = ""
)
package com.hiden.movies.data.entity

import com.google.gson.annotations.SerializedName

data class PlayerResponse(
        @SerializedName("checked_in_at")
        val checkedInAt: String ="",
        @SerializedName("created_at")
        val createdAt: String = "",
        @SerializedName("id")
        val id: Int = 0,
        @SerializedName("is_active")
        val isActive: Boolean = false,
        @SerializedName("profile")
        val profile: Profile,
        @SerializedName("team")
        val team: String?,
        @SerializedName("tournament")
        val tournament: Int = 0,
        @SerializedName("updated_at")
        val updatedAt: String = "",
        @SerializedName("user")
        val user: Int = 0
) {
    data class Profile(
            @SerializedName("clash_royale_tag")
            val clashRoyaleTag: String?,
            @SerializedName("first_name")
            val firstName: String = "",
            @SerializedName("last_name")
            val lastName: String = "",
            @SerializedName("user_id")
            val userId: Int = 0,
            @SerializedName("username")
            val username: String = ""
    )

}
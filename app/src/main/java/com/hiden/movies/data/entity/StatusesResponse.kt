package com.hiden.movies.data.entity

import com.google.gson.annotations.SerializedName

data class StatusesResponse(
        @SerializedName("created_at") val created_at : String,
        @SerializedName("id") val id : Long,
        @SerializedName("id_str") val id_str : String,
        @SerializedName("text") val text : String,
        @SerializedName("truncated") val truncated : Boolean,
        @SerializedName("entities") val entities : Entities,
        @SerializedName("source") val source : String,
        @SerializedName("in_reply_to_status_id") val in_reply_to_status_id : String,
        @SerializedName("in_reply_to_status_id_str") val in_reply_to_status_id_str : String,
        @SerializedName("in_reply_to_user_id") val in_reply_to_user_id : String,
        @SerializedName("in_reply_to_user_id_str") val in_reply_to_user_id_str : String,
        @SerializedName("in_reply_to_screen_name") val in_reply_to_screen_name : String,
        @SerializedName("user") val user : User,
        @SerializedName("geo") val geo : String,
        @SerializedName("coordinates") val coordinates : String,
        @SerializedName("place") val place : String,
        @SerializedName("contributors") val contributors : String,
        @SerializedName("is_quote_status") val is_quote_status : Boolean,
        @SerializedName("retweet_count") val retweet_count : Int,
        @SerializedName("favorite_count") val favorite_count : Int,
        @SerializedName("favorited") val favorited : Boolean,
        @SerializedName("retweeted") val retweeted : Boolean,
        @SerializedName("lang") val lang : String
) {
    data class Description (

            @SerializedName("urls") val urls : List<String>
    )

    data class Entities (

            @SerializedName("description") val description : Description
    )

    data class User (

            @SerializedName("id") val id : Long,
            @SerializedName("id_str") val id_str : String,
            @SerializedName("name") val name : String,
            @SerializedName("screen_name") val screen_name : String,
            @SerializedName("location") val location : String,
            @SerializedName("description") val description : String,
            @SerializedName("url") val url : String,
            @SerializedName("entities") val entities : Entities,
            @SerializedName("protected") val protected : Boolean,
            @SerializedName("followers_count") val followers_count : Int,
            @SerializedName("friends_count") val friends_count : Int,
            @SerializedName("listed_count") val listed_count : Int,
            @SerializedName("created_at") val created_at : String,
            @SerializedName("favourites_count") val favourites_count : Int,
            @SerializedName("utc_offset") val utc_offset : String,
            @SerializedName("time_zone") val time_zone : String,
            @SerializedName("geo_enabled") val geo_enabled : Boolean,
            @SerializedName("verified") val verified : Boolean,
            @SerializedName("statuses_count") val statuses_count : Int,
            @SerializedName("lang") val lang : String,
            @SerializedName("contributors_enabled") val contributors_enabled : Boolean,
            @SerializedName("is_translator") val is_translator : Boolean,
            @SerializedName("is_translation_enabled") val is_translation_enabled : Boolean,
            @SerializedName("profile_background_color") val profile_background_color : String,
            @SerializedName("profile_background_image_url") val profile_background_image_url : String,
            @SerializedName("profile_background_image_url_https") val profile_background_image_url_https : String,
            @SerializedName("profile_background_tile") val profile_background_tile : Boolean,
            @SerializedName("profile_image_url") val profile_image_url : String,
            @SerializedName("profile_image_url_https") val profile_image_url_https : String,
            @SerializedName("profile_banner_url") val profile_banner_url : String,
            @SerializedName("profile_link_color") val profile_link_color : String,
            @SerializedName("profile_sidebar_border_color") val profile_sidebar_border_color : String,
            @SerializedName("profile_sidebar_fill_color") val profile_sidebar_fill_color : String,
            @SerializedName("profile_text_color") val profile_text_color : String,
            @SerializedName("profile_use_background_image") val profile_use_background_image : Boolean,
            @SerializedName("has_extended_profile") val has_extended_profile : Boolean,
            @SerializedName("default_profile") val default_profile : Boolean,
            @SerializedName("default_profile_image") val default_profile_image : Boolean,
            @SerializedName("following") val following : Boolean,
            @SerializedName("follow_request_sent") val follow_request_sent : Boolean,
            @SerializedName("notifications") val notifications : Boolean,
            @SerializedName("translator_type") val translator_type : String
    )
}
package com.hiden.movies.presentation.model


data class UserStatusDataView(
        val id : Long,
        val created_at : String,
        val text : String,
        val user_name : String,
        val retweeted : Boolean = false,
        val is_quote_status : Boolean = false
        )
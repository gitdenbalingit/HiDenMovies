//package com.hiden.movies.presentation.ui.postdetail
//
//import android.os.Bundle
//import com.hiden.movies.R
//import com.hiden.movies.data.di.GlideApp
//import com.hiden.movies.presentation.AppActivity
//import kotlinx.android.synthetic.main.activity_post_detail.*
//
//class PostDetailActivity: AppActivity(){
//
//    companion object {
//        const val MAX_MESSAGE = "INTENT_POST"
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_post_detail)
//        setupToolbar()
//        setView()
//    }
//
//    private fun setupToolbar() {
//        setSupportActionBar(toolbar)
//        supportActionBar?.apply {
//            title = "Tweet"
//            setDisplayHomeAsUpEnabled(true)
//            setDisplayShowHomeEnabled(true)
//        }
//    }
//
//    private fun setView(){
//
//        user_name.text = "User name:"+item.user_name
//        user_post.text = "Status:"+item.text
//        retweeted.text = "Retweeted: Yes".takeIf { item.retweeted } ?: "Retweeted: No"
//        quote.text = "Quote status: Yes".takeIf { item.is_quote_status } ?: "Quote status: No"
//       date.text = item.created_at
//
//        GlideApp.with(itemView)
//                .load(item.user_avatar)
//                .into(itemView.avatar)
//
//    }
//}
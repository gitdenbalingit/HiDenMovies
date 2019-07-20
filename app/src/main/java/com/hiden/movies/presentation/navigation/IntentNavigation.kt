package com.hiden.movies.presentation.navigation

import android.app.Activity
import com.hiden.movies.presentation.common.ext.transitionRightToLeft
import com.hiden.movies.presentation.ui.detail.DetailActivity
import com.hiden.movies.presentation.ui.post.PostStatusActivity
import com.hiden.movies.presentation.ui.searchtweet.SearchTweetActivity
import org.jetbrains.anko.intentFor
import javax.inject.Inject
class DetailScreenNavigator @Inject constructor(private val activity: Activity){
    fun navigate(id: Int){
        activity.apply {
            transitionRightToLeft(intentFor<DetailActivity>(DetailActivity.KEY_MOVIE_ID to id))
        }
    }
}


class SearchScreenNavigator @Inject constructor(private val activity: Activity){
    fun navigate(){
        activity.apply {
            transitionRightToLeft(intentFor<SearchTweetActivity>())
        }
    }
}

class PostScreenNavigator @Inject constructor(private val activity: Activity){
    fun navigate(){
        activity.apply {
            transitionRightToLeft(intentFor<PostStatusActivity>())
        }
    }
}



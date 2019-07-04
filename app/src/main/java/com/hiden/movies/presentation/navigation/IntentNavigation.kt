package com.hiden.movies.presentation.navigation

import android.app.Activity
import com.hiden.movies.presentation.common.ext.transitionRightToLeft
import com.hiden.movies.presentation.ui.detail.DetailActivity
import org.jetbrains.anko.intentFor
import javax.inject.Inject
class DetailScreenNavigator @Inject constructor(private val activity: Activity){
    fun navigate(id: Int){
        activity.apply {
            transitionRightToLeft(intentFor<DetailActivity>(DetailActivity.KEY_MOVIE_ID to id))
        }
    }
}

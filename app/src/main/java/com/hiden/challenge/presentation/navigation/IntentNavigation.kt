package com.hiden.challenge.presentation.navigation

import android.app.Activity
import com.hiden.challenge.presentation.common.ext.transitionRightToLeft
import com.hiden.challenge.presentation.ui.chooseplayers.ChoosePlayersActivity
import com.hiden.challenge.presentation.ui.choosestage.ChooseStageActivity
import com.hiden.challenge.presentation.ui.detail.DetailActivity
import org.jetbrains.anko.intentFor
import javax.inject.Inject
class DetailScreenNavigator @Inject constructor(private val activity: Activity){
    fun navigate(id: Int){
        activity.apply {
            transitionRightToLeft(intentFor<DetailActivity>(DetailActivity.KEY_MOVIE_ID to id))
        }
    }
}

class ChooseStageNavigator @Inject constructor(private  val activity: Activity) {
    fun navigate(){
        activity.apply {
            startActivity(intentFor<ChooseStageActivity>())
        }
    }
}

class ChoosePlayersNavigator @Inject constructor(private  val activity: Activity) {
    fun navigate(){
        activity.apply {
            startActivity(intentFor<ChoosePlayersActivity>())
        }
    }
}

package com.hiden.challenge.presentation.navigation

import android.app.Activity
import com.hiden.challenge.presentation.ui.chooseplayers.ChoosePlayersActivity
import com.hiden.challenge.presentation.ui.choosestage.ChooseStageActivity
import org.jetbrains.anko.intentFor
import javax.inject.Inject

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

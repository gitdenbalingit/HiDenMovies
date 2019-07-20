package com.hiden.movies.presentation.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.hiden.movies.presentation.AppActivity
import com.hiden.movies.R
import com.hiden.movies.data.di.GlideApp
import com.hiden.movies.presentation.common.adapter.UserStatusAdapter
import com.hiden.movies.presentation.common.ext.observe
import com.hiden.movies.presentation.common.ext.transitionLeftToRight
import com.hiden.movies.presentation.common.ext.withViewModel
import com.hiden.movies.presentation.model.UserDataView
import com.hiden.movies.presentation.model.UserStatusDataView
import com.hiden.movies.presentation.navigation.PostScreenNavigator
import com.hiden.movies.presentation.navigation.SearchScreenNavigator
import kotlinx.android.synthetic.main.activity_user_profile.*
import javax.inject.Inject


class UserProfileActivity: AppActivity(), UserStatusAdapter.Delegate {


    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var userStatusAdapter: UserStatusAdapter

    @Inject lateinit var goToSearchScreen: SearchScreenNavigator
    @Inject lateinit var goToPostScreen: PostScreenNavigator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        setupStatus()
        observerViewModel()

        search.setOnClickListener {
            goToSearchScreen.navigate()
        }

        post.setOnClickListener {
            goToPostScreen.navigate()
        }

    }

    private fun setupStatus(){
        status_recycler.isNestedScrollingEnabled = false
        status_recycler.apply {
            adapter = userStatusAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        withViewModel<UserProfileViewModel>(viewModelFactory) {
            loadUserStatuses()
        }
    }


    private fun observerViewModel(){
        withViewModel<UserProfileViewModel>(viewModelFactory) {
            loadUser()
//            loadUserStatuses()
            observe(userData, ::loadUserInfo)
            observe<Result<List<UserStatusDataView>>, LiveData<Result<List<UserStatusDataView>>>>(statusesData, ::loadUserStatuses)
        }
    }

    private fun loadUserInfo(result: Result<UserDataView>?){
        if(result == null) return
        result.onSuccess {user ->
            user_name.text = user.name
            user_desc.text = user.description
            GlideApp.with(this)
                    .load(user.profile_image_url)
                    .into(profile_avatar)

            GlideApp.with(this)
                    .load(user.profile_background_image_url)
                    .into(cover_photo)
        }
    }

    private fun loadUserStatuses(result: Result<List<UserStatusDataView>>?){
        if(result == null) return
        result.onSuccess { list ->
            userStatusAdapter.setItems(list)
        }
    }

    override fun onStatusItemClicked(userStatusDataView: UserStatusDataView) {
        Log.v("PIA" , "click - "+userStatusDataView.text)
    }

}
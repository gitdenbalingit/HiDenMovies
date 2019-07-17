package com.hiden.movies.presentation.ui.profile

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.hiden.movies.presentation.AppActivity
import com.hiden.movies.R
import com.hiden.movies.presentation.common.ext.observe
import com.hiden.movies.presentation.common.ext.withViewModel
import com.hiden.movies.presentation.model.UserDataView
import com.hiden.movies.presentation.ui.detail.DetailActivityViewModel
import kotlinx.android.synthetic.main.activity_user_profile.*
import javax.inject.Inject


class UserProfileActivity: AppActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        observerViewModel()

    }


    private fun observerViewModel(){
        withViewModel<UserProfileViewModel>(viewModelFactory) {
            loadUser()
            observe(userData, ::loadUserInfo)
        }
    }

    private fun loadUserInfo(result: Result<UserDataView>?){
        if(result == null) return
        result.onSuccess {user ->
            user_name.text = user.name
            user_desc.text = user.description
        }
    }

}
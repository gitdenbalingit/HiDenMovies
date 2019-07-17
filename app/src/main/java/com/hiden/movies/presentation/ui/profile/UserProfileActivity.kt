package com.hiden.movies.presentation.ui.profile

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.hiden.movies.presentation.AppActivity
import com.hiden.movies.R
import com.hiden.movies.data.di.GlideApp
import com.hiden.movies.presentation.common.adapter.UserStatusAdapter
import com.hiden.movies.presentation.common.ext.observe
import com.hiden.movies.presentation.common.ext.withViewModel
import com.hiden.movies.presentation.model.UserDataView
import com.hiden.movies.presentation.model.UserStatusDataView
import com.hiden.movies.presentation.ui.post.PostStatusActivity
import com.hiden.movies.presentation.ui.searchtweet.SearchTweetActivity
import kotlinx.android.synthetic.main.activity_user_profile.*
import org.jetbrains.anko.intentFor
import javax.inject.Inject


class UserProfileActivity: AppActivity(), UserStatusAdapter.Delegate {


    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var userStatusAdapter: UserStatusAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        setupStatus()
        observerViewModel()

        search.setOnClickListener {
            startActivity(intentFor<SearchTweetActivity>())
        }

        post.setOnClickListener {
            startActivity(intentFor<PostStatusActivity>())
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
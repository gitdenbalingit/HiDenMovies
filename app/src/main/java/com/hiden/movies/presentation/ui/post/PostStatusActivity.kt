package com.hiden.movies.presentation.ui.post

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.hiden.movies.R
import com.hiden.movies.data.entity.PostStatusResponse
import com.hiden.movies.presentation.AppActivity
import com.hiden.movies.presentation.common.ext.observe
import com.hiden.movies.presentation.common.ext.withViewModel
import com.jakewharton.rxbinding2.widget.RxTextView
import kotlinx.android.synthetic.main.activity_post_tweet.*
import javax.inject.Inject

class PostStatusActivity: AppActivity() {

    companion object {
        const val MAX_MESSAGE = 14
    }

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_tweet)
        setupToolbar()
        observeViewModel()


        post.setOnClickListener {
            withViewModel<PostStatusViewModel>(viewModelFactory) {
                postStatus(text.text.toString())
            }
        }





        disposableContainer.add( RxTextView.textChangeEvents(text)
                .map { it.count() }
                .filter { (MAX_MESSAGE - it) >= 0 }
                .doOnNext { characters.text = (MAX_MESSAGE - it).toString() }
                .subscribe())




    }

    private fun observeViewModel(){
        withViewModel<PostStatusViewModel>(viewModelFactory) {
            observe(postResponse, ::handlePost)
        }
    }

    private fun handlePost(result: Result<PostStatusResponse>?){
        if(result == null) return
        result.onSuccess {
            finish()
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "New Tweet"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }
}
package com.hiden.movies.presentation.ui.searchtweet

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.fotoku.mobile.libs.rx.observable.RxSearchViewSupport
import com.hiden.movies.R
import com.hiden.movies.presentation.AppActivity
import com.hiden.movies.presentation.common.adapter.SearchTweetAdapter
import com.hiden.movies.presentation.common.ext.observe
import com.hiden.movies.presentation.common.ext.withViewModel
import com.hiden.movies.presentation.model.UserStatusDataView
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.abc_search_view.view.*
import kotlinx.android.synthetic.main.activity_search_movie.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchTweetActivity: AppActivity(), SearchTweetAdapter.Delegate {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var searchTweetAdapter: SearchTweetAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_tweet)
        setupSearchView()
        setupList()
        observeViewModel()
    }


    private fun observeViewModel(){
        withViewModel<SearchTweetViewModel>(viewModelFactory) {
            observe(searchStatusesData, ::displaySearchResults)
        }
    }

    private fun setupList(){
        searchRecycler.isNestedScrollingEnabled = false
        searchRecycler.apply {
            adapter = searchTweetAdapter
        }
    }

    private fun displaySearchResults(result: Result<List<UserStatusDataView>>?){
        if(result == null) return
        result.onSuccess { list ->
            searchTweetAdapter.setItems(list)
        }
    }


    private fun setupSearchView() {
        searchView.onActionViewExpanded()
        searchView.maxWidth = Integer.MAX_VALUE

        val v = searchView.search_plate
        v.setBackgroundColor(Color.TRANSPARENT)

        disposableContainer.add(
                RxSearchViewSupport.changes(searchView).throttleFirst(
                        500,
                        TimeUnit.MILLISECONDS,
                        AndroidSchedulers.mainThread()
                )
                        .filter { searchQuery -> searchQuery.isNullOrBlank() || searchQuery.length >= 3 }
                        .subscribe({ query ->
                            withViewModel<SearchTweetViewModel>(viewModelFactory) {
                                searchTweet(query)

                            }
                        }, {})
        )

        val closeImageView = searchView.findViewById(R.id.search_close_btn) as ImageView
        if (closeImageView != null) {
            closeImageView.setImageDrawable(resources.getDrawable(R.drawable.transparentbg))
            closeImageView.isEnabled = false
        }

        val searchTextView = searchView.findViewById(R.id.search_src_text) as EditText
        if (searchTextView != null) {

            val params = searchTextView.layoutParams as LinearLayout.LayoutParams
            params.topMargin = 0
            params.bottomMargin = 0
            searchTextView.layoutParams = params
            searchTextView.setHintTextColor(resources.getColor(R.color.view_divider_thin_color))
            searchTextView.hint = "Looking for?"
            searchTextView.setTextColor(resources.getColor(R.color.primary_text_color))

            val search_text = searchView.search_src_text as AutoCompleteTextView
            search_text.setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    resources.getDimensionPixelSize(R.dimen.sp_26).toFloat()
            )

            try {
                val mCursorDrawableRes = TextView::class.java.getDeclaredField("mCursorDrawableRes")
                mCursorDrawableRes.isAccessible = true
                mCursorDrawableRes.set(searchTextView, R.drawable.search_cursor)
            } catch (e: Exception) {
            }

        }
    }




    override fun onStatusItemClicked(userStatusDataView: UserStatusDataView) {
        Log.v("PIA" , "click - "+userStatusDataView.text)
    }
}
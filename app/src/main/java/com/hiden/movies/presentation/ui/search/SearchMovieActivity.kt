package com.hiden.movies.presentation.ui.search

import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.fotoku.mobile.libs.rx.observable.RxSearchViewSupport
import com.hiden.movies.R
import com.hiden.movies.data.entity.MovieResponse
import com.hiden.movies.presentation.AppActivity
import com.hiden.movies.presentation.common.adapter.SearchMovieAdapter
import com.hiden.movies.presentation.common.adapter.SearchMoviePagedAdapter
import com.hiden.movies.presentation.common.ext.withViewModel
import com.hiden.movies.presentation.model.State
import com.hiden.movies.presentation.navigation.DetailScreenNavigator
import com.hiden.movies.presentation.ui.genericview.ItemDecorationAlbumColumns
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_search_movie.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchMovieActivity : AppActivity(), SearchMovieAdapter.Delegate {


    @Inject lateinit var detailScreenNavigator: DetailScreenNavigator
    lateinit var searchMoviePagedAdapter: SearchMoviePagedAdapter

    private val viewModel: SearchMovieViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movie)
        setupSearchView()
        setupSearchList()


        with(viewModel) {
            listing.observe(this@SearchMovieActivity, Observer {
                searchMoviePagedAdapter.submitList(it)

            })
        }

    }

    private fun setupSearchList() {

        searchMoviePagedAdapter = SearchMoviePagedAdapter({
            withViewModel<SearchMovieViewModel>(viewModelFactory) {
                retry()
            }
        }) {

            detailScreenNavigator.navigate(it.id)
        }

        searchMoviePagedAdapter.setState(State.DONE)


        val itemCategoryDecorationAlbumColumns = ItemDecorationAlbumColumns(resources.getDimensionPixelSize(R.dimen.dp_7), 2)

        searchRecycler.isNestedScrollingEnabled = true
        searchRecycler.apply {
            adapter = searchMoviePagedAdapter
            addItemDecoration(itemCategoryDecorationAlbumColumns)
        }
    }


    override fun onMovieItemClicked(movieResponse: MovieResponse) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private fun setupSearchView() {
        searchView.onActionViewExpanded()
        searchView.maxWidth = Integer.MAX_VALUE

//        val v = searchView.search_plate
//        v.setBackgroundColor(Color.TRANSPARENT)

        disposableContainer.add(
            RxSearchViewSupport.changes(searchView).throttleFirst(
                500,
                TimeUnit.MILLISECONDS,
                AndroidSchedulers.mainThread()
            )
                .filter { searchQuery -> searchQuery.isNullOrBlank() || searchQuery.length >= 3 }
                .subscribe({ query ->
                    withViewModel<SearchMovieViewModel>(viewModelFactory) {
                        searchMovieWithPage(query)
                        listing.observe(this@SearchMovieActivity, Observer {
                            searchMoviePagedAdapter.submitList(it)

                        })
                    }
                },
                    {})
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

//            val search_text = searchView.search_src_text as AutoCompleteTextView
//            search_text.setTextSize(
//                TypedValue.COMPLEX_UNIT_PX,
//                resources.getDimensionPixelSize(R.dimen.sp_26).toFloat()
//            )

            try {
                val mCursorDrawableRes = TextView::class.java.getDeclaredField("mCursorDrawableRes")
                mCursorDrawableRes.isAccessible = true
                mCursorDrawableRes.set(searchTextView, R.drawable.search_cursor)
            } catch (e: Exception) {
            }

        }
    }

}
package com.hiden.movies.presentation.ui.main

import android.annotation.TargetApi
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.hiden.movies.R
import com.hiden.movies.data.di.GlideApp
import com.hiden.movies.presentation.AppActivity
import com.hiden.movies.presentation.util.IMAGE_BASE_URL_780
import com.hiden.movies.presentation.common.adapter.MoviesAdapter
import com.hiden.movies.presentation.common.ext.observe
import com.hiden.movies.presentation.common.ext.withViewModel
import com.hiden.movies.presentation.common.rx.RxAppBar
import com.hiden.movies.presentation.common.rx.RxRecyclerView
import com.hiden.movies.presentation.model.MovieDataView
import com.hiden.movies.presentation.navigation.DetailScreenNavigator
import com.hiden.movies.presentation.ui.search.SearchMovieActivity
import com.hiden.movies.presentation.util.getScreenHeight
import com.hiden.movies.presentation.util.getScreenWidth
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import javax.inject.Inject

class MainActivity: AppActivity(), MoviesAdapter.Delegate {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var detailScreenNavigator: DetailScreenNavigator

    @Inject lateinit var moviesAdapter: MoviesAdapter

    @Inject lateinit var discoverMoviesAdapter: MoviesAdapter
    @Inject lateinit var upcomingMoviesAdapter: MoviesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setView()
        loadContents()
        observeViewModel()


    }

    private fun loadContents(){
        withViewModel<MainActivityViewModel>(viewModelFactory) {
            loadNowShowingMovie()
            loadTopRatedMovies()
            loadDiscoverMovies()
            loadUpcomingMovies()
        }
    }

    private fun observeViewModel(){
        withViewModel<MainActivityViewModel>(viewModelFactory) {
            loadUser()
            loadUserStatuses()
            searchTweet()
//            postStatus()
            observe(converItem, ::displayCoverImage)
            observe(topRatedList, ::loadTopRatedContents)
            observe(discoverList, ::loadDiscoverContents)
            observe(upcomingList, ::loadUpcomingContents)
        }
    }

    private fun setView(){
        appBarLayout.layoutParams.height = (getScreenHeight() * .45).toInt()
        appBarLayout.layoutParams.width = getScreenWidth()
        discoverRecycler.setPadding((getScreenWidth() * .55).toInt(), 0, 0, 0)


        disposableContainer.add(RxAppBar.isExpanded(appBarLayout)
            .distinctUntilChanged()
            .subscribe(this::setToolbarIcons))

        disposableContainer.add(RxAppBar.alphaValueChanges(appBarLayout)
            .doOnNext { aFloat -> toolbar_elevation.alpha = (1 - aFloat) }
            .subscribe(tempBg::setAlpha))

       disposableContainer.add( RxRecyclerView.onScrollXChanges(discoverRecycler)
            .doOnNext { it ->
                discover_tile.alpha = 1f -
                        (0.3f + Math.abs(discoverRecycler.computeHorizontalScrollOffset() / discoverRecycler.computeHorizontalScrollRange().toFloat()))
            }
            .doOnNext { it ->
                discover_img.alpha = 1f -
                        (0.3f + Math.abs(discoverRecycler.computeHorizontalScrollOffset() / discoverRecycler.computeHorizontalScrollRange().toFloat()))
            }
            .subscribe { dx -> discover_tile.x = discover_tile.x - dx / 6 })

        searchHolder.setOnClickListener{
            startActivity(intentFor<SearchMovieActivity>())
        }




        topRatedRecycler.isNestedScrollingEnabled = false
        topRatedRecycler.apply {
            adapter = moviesAdapter
        }

        discoverRecycler.isNestedScrollingEnabled = false
        discoverRecycler.apply {
            adapter = discoverMoviesAdapter
        }

        upcomingRecycler.isNestedScrollingEnabled = false
        upcomingRecycler.apply {
            adapter = upcomingMoviesAdapter
        }
    }

    private fun displayCoverImage(result: Result<MovieDataView>?){
        if(result == null) return
        result.onSuccess {
            GlideApp.with(this)
                .load(IMAGE_BASE_URL_780 + it.backdropPath)
                .into(coverImage)

            now_showing_title.text = it.title
        }
    }

    private fun loadDiscoverContents(result: Result<List<MovieDataView>>?){
        if(result == null) return
        result.onSuccess {
            discoverMoviesAdapter.setItems(it)
        }
    }

    private fun loadTopRatedContents(result: Result<List<MovieDataView>>?){
        if(result == null) return
        result.onSuccess {
            moviesAdapter.setItems(it)
        }
    }

    private fun loadUpcomingContents(result: Result<List<MovieDataView>>?){
        if(result == null) return
        result.onSuccess {
            upcomingMoviesAdapter.setItems(it)
        }
    }



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setToolbarIcons(isExpanded: Boolean) {
        window.statusBarColor = if (isExpanded) Color.parseColor("#1F1F1F") else Color.WHITE
        window.decorView.systemUiVisibility = if (isExpanded) 0 else View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        more.setTextColor(
            if (isExpanded) Color.WHITE else resources.getColor(R.color.primary_text_color)
        )
        more.background = resources.getDrawable(
            if (isExpanded)
                R.drawable.rectangle_shape_border_white
            else
                R.drawable.rectangle_shape_border_dark
        )
        searchHint.setTextColor(if (isExpanded) Color.WHITE else Color.parseColor("#88d8d6d2"))
        searchHolder.background = resources.getDrawable(
            if (isExpanded)
                R.drawable.rectangle_shape_border_white
            else
                R.drawable.rectangle_shape_border_dark
        )
        searchIcon.setImageResource(
            if (isExpanded) R.drawable.ic_search_white_24dp else R.drawable.ic_search_gray
        )
    }

    override fun onMovieItemClicked(movieDataView: MovieDataView) {
        detailScreenNavigator.navigate(movieDataView.id)
    }
}
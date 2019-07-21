package com.hiden.challenge.presentation.ui.detail

import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.hiden.challenge.R
import com.hiden.challenge.data.di.GlideApp
import com.hiden.challenge.data.entity.Mapper.toDataView
import com.hiden.challenge.presentation.AppActivity
import com.hiden.challenge.presentation.util.IMAGE_BASE_URL_500
import com.hiden.challenge.presentation.util.IMAGE_BASE_URL_780
import com.hiden.challenge.presentation.common.adapter.MoviesAdapter
import com.hiden.challenge.presentation.common.ext.observe
import com.hiden.challenge.presentation.common.ext.withViewModel
import com.hiden.challenge.presentation.model.MovieDataView
import com.hiden.challenge.presentation.model.MovieDetailView
import com.hiden.challenge.presentation.navigation.DetailScreenNavigator
import kotlinx.android.synthetic.main.activity_detail.*
import java.text.DecimalFormat
import javax.inject.Inject

class DetailActivity: AppActivity(), MoviesAdapter.Delegate {

    companion object {
        const val KEY_MOVIE_ID = "DetailActivity.movie-id"
    }
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var similarMoviesAdapter: MoviesAdapter
    @Inject lateinit var detailScreenNavigator: DetailScreenNavigator


    private val movieId by lazy { intent.getIntExtra(KEY_MOVIE_ID, 0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        loadMovieDetail()
        observerViewModel()



    }


    private fun loadMovieDetail(){
        withViewModel<DetailActivityViewModel>(viewModelFactory) {
            loadMovieDetail(movieId)
        }
    }

    private fun observerViewModel(){
        withViewModel<DetailActivityViewModel>(viewModelFactory) {
            observe(movieData, ::loadContents)
        }
    }

    private fun loadContents(result: Result<MovieDetailView>?) {
        if(result == null) return
        result.onSuccess {
            setView(it)
        }
    }

    override fun onMovieItemClicked(movieDataView: MovieDataView) {
        detailScreenNavigator.navigate(movieDataView.id)
        finish()
    }

    private fun setView(data: MovieDetailView){
        val runtime_hr = (data.runtime!! /60).toString() + "h"
        val runtime_mins = (data.runtime!!%60).toString() +"m"

        similarRecycler.isNestedScrollingEnabled = false
        similarRecycler.apply {
            adapter = similarMoviesAdapter
        }

        GlideApp.with(this)
            .load(IMAGE_BASE_URL_780 + data.posterPath)
            .into(movie_backdrop)

        GlideApp.with(this)
            .load(IMAGE_BASE_URL_500 + data.backdropPath)
            .into(movie_poster_detail)

        movie_title.text = data.originalTitle
        movie_tag_line.text = data.tagline
        movie_rating.text = "Rating: " + data.voteAverage.toString() +"/10"
        vote_count.text = "Votes: " +data.voteCount
        release_date.text = data.releaseDate.take(4)
        language.text = data.originalLanguage.toUpperCase()
        running_time.text = "$runtime_hr $runtime_mins"
        movie_status.text = data.status
        overview.text = data.overview
        genre_values.text = data.genres.map { it.name }.toList().joinToString(separator = ", ")
        budget_values.text = "$" +DecimalFormat("#,###").format(data.budget)
        revenue_values.text = "$" +DecimalFormat("#,###").format(data.revenue)

        similarMoviesAdapter.setItems(data.similar.results.map { it.toDataView() })



    }
}
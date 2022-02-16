package com.hiden.movies.presentation.ui.detail

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import com.hiden.movies.R
import com.hiden.movies.data.di.GlideApp
import com.hiden.movies.data.entity.Mapper.toDataView
import com.hiden.movies.presentation.AppActivity
import com.hiden.movies.presentation.util.IMAGE_BASE_URL_500
import com.hiden.movies.presentation.util.IMAGE_BASE_URL_780
import com.hiden.movies.presentation.common.adapter.MoviesAdapter
import com.hiden.movies.presentation.common.ext.observe
import com.hiden.movies.presentation.model.MovieDataView
import com.hiden.movies.presentation.model.MovieDetailView
import com.hiden.movies.presentation.navigation.DetailScreenNavigator
import kotlinx.android.synthetic.main.activity_detail.*
import java.text.DecimalFormat
import javax.inject.Inject

class DetailActivity: AppActivity(), MoviesAdapter.Delegate {

    companion object {
        const val KEY_MOVIE_ID = "DetailActivity.movie-id"
    }

    @Inject lateinit var similarMoviesAdapter: MoviesAdapter
    @Inject lateinit var detailScreenNavigator: DetailScreenNavigator
    private val viewModel: DetailActivityViewModel by viewModels()



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
        with(viewModel) {
            loadMovieDetail(movieId)
        }
    }

    private fun observerViewModel(){
        with(viewModel) {
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
        val runtimeHr = data.runtime.toString() + "h"
        val runtimeMins = (data.runtime?.rem(60)).toString() +"m"

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
        running_time.text = "$runtimeHr $runtimeMins"
        movie_status.text = data.status
        overview.text = data.overview
        genre_values.text = data.genres.map { it.name }.toList().joinToString(separator = ", ")
        budget_values.text = "$" +DecimalFormat("#,###").format(data.budget)
        revenue_values.text = "$" +DecimalFormat("#,###").format(data.revenue)

        similarMoviesAdapter.setItems(data.similar.results.map { it.toDataView() })



    }
}
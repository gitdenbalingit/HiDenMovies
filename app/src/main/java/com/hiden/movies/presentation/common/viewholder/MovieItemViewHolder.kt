package com.hiden.movies.presentation.common.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hiden.movies.R
import com.hiden.movies.data.di.GlideApp
import com.hiden.movies.presentation.util.IMAGE_BASE_URL_154
import com.hiden.movies.presentation.common.adapter.MoviesAdapter
import com.hiden.movies.presentation.model.MovieDataView
import com.hiden.movies.presentation.util.getScreenWidth
import com.hiden.movies.presentation.util.pixelsToSp
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieItemViewHolder(
    itemView: View,
    private val callback: MoviesAdapter.Delegate
) : BindingViewHolder<MovieDataView>(itemView) {



    private var movieDataView: MovieDataView? = null

    class Factory(private val delegate: MoviesAdapter.Delegate) :
            ViewHolderFactory<BindingViewHolder<MovieDataView>> {
        override fun create(parent: ViewGroup) = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
            .let { MovieItemViewHolder(it, delegate)}
    }

    init {

        val computedWidth = (itemView.context.getScreenWidth() / 3.6).toInt()
        val computedHeight = (computedWidth + computedWidth / 2)

        val params = itemView.movie_image.layoutParams

        params.width = computedWidth
        params.height = computedHeight



        itemView.movie_title.textSize = itemView.context.pixelsToSp((computedHeight / 12).toFloat())

        itemView.setOnClickListener {
            movieDataView?.let { callback.onMovieItemClicked(it) }
        }
    }



    override fun bind(item: MovieDataView) {
        this.movieDataView = item
        itemView.movie_title.text = item.originalTitle

        GlideApp.with(itemView)
            .load(IMAGE_BASE_URL_154 + item.posterPath)
//            .placeholder(R.drawable.bg_rounded_corner_2dp)
//            .error(R.drawable.ic_broken_image)
            .into(itemView.movie_image)

    }

}
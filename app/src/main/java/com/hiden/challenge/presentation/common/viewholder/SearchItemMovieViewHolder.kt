package com.hiden.challenge.presentation.common.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hiden.challenge.R
import com.hiden.challenge.data.di.GlideApp
import com.hiden.challenge.data.entity.MovieResponse
import com.hiden.challenge.presentation.util.IMAGE_BASE_URL_154
import com.hiden.challenge.presentation.common.adapter.SearchMovieAdapter
import kotlinx.android.synthetic.main.item_search_movie.view.*

class SearchItemMovieViewHolder(
    itemView: View,
    private val callback: SearchMovieAdapter.Delegate
) : BindingViewHolder<MovieResponse>(itemView) {



    private var movieResponse: MovieResponse? = null

    class Factory(private val delegate: SearchMovieAdapter.Delegate) : ViewHolderFactory<BindingViewHolder<MovieResponse>> {
        override fun create(parent: ViewGroup) = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_search_movie, parent, false)
            .let { SearchItemMovieViewHolder(it, delegate)}
    }

    init {
        itemView.setOnClickListener {
            movieResponse?.let { callback.onMovieItemClicked(it) }
        }
    }



    override fun bind(item: MovieResponse) {
        this.movieResponse = item
        itemView.search_title.text = item.originalTitle

        GlideApp.with(itemView)
            .load(IMAGE_BASE_URL_154 + item.posterPath)
//            .placeholder(R.drawable.bg_rounded_corner_2dp)
//            .error(R.drawable.ic_broken_image)
            .into(itemView.search_image)

    }

}
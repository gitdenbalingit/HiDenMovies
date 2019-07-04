package com.hiden.movies.presentation.common.viewholder

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hiden.movies.R
import com.hiden.movies.data.di.GlideApp
import com.hiden.movies.data.entity.MovieResponse
import com.hiden.movies.presentation.util.IMAGE_BASE_URL_154
import com.hiden.movies.presentation.util.getScreenWidth
import kotlinx.android.synthetic.main.item_search_movie.view.*

class SearchItemWithPageViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    init {
        val computedWidth = (itemView.context.getScreenWidth() / 2.8).toInt()
        val computedHeight = (computedWidth + computedWidth / 2.5).toInt()


        val params = itemView.search_image.layoutParams
        val titleParams = itemView.search_title.layoutParams

        params.width = computedWidth
        params.height = computedHeight

        titleParams.width = computedWidth

        itemView.search_title.layoutParams = titleParams

        itemView.gradientOverlay.layoutParams = params
        itemView.search_image.layoutParams = params
    }

    fun bind(movieResponse: MovieResponse?, clickListener : (MovieResponse) -> Unit) {
        if (movieResponse != null) {
            itemView.search_title.text = movieResponse.originalTitle

            GlideApp.with(itemView)
                .load(IMAGE_BASE_URL_154 + movieResponse.posterPath)
                .into(itemView.search_image)

            itemView.setOnClickListener {
                Log.v("DEN","set")
                clickListener(movieResponse)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): SearchItemWithPageViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_movie, parent, false)
            return SearchItemWithPageViewHolder(view)
        }
    }
}
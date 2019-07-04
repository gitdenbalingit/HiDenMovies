package com.hiden.movies.presentation.common.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hiden.movies.presentation.common.viewholder.BindingViewHolder
import com.hiden.movies.presentation.common.viewholder.ViewHolderFactory
import com.hiden.movies.presentation.model.MovieDataView
import javax.inject.Inject

class MoviesAdapter @Inject constructor(
    private val viewHolderFactory: ViewHolderFactory<BindingViewHolder<MovieDataView>>
) : RecyclerView.Adapter<BindingViewHolder<MovieDataView>>() {

    interface Delegate {
        fun onMovieItemClicked(movieResponse: MovieDataView)
    }

    private val items = mutableListOf<MovieDataView>()

    fun setItems(newItems: List<MovieDataView>) {
        val result = DiffUtil.calculateDiff(
            DiffCallback(items, newItems)
        )

        items.clear()
        items.addAll(newItems)
        result.dispatchUpdatesTo(this)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = viewHolderFactory.create(parent)

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BindingViewHolder<MovieDataView>, position: Int) =
        holder.bind(items[position])


    class DiffCallback(
        private val oldList: List<MovieDataView>,
        private val newList: List<MovieDataView>
    ) :
        DiffUtil.Callback() {

        override fun areItemsTheSame(
            oldItemPosition: Int,
            newItemPosition: Int
        ) = oldList[oldItemPosition].id == newList[newItemPosition].id

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areContentsTheSame(
            oldItemPosition: Int,
            newItemPosition: Int
        ) = oldList[oldItemPosition] == newList[newItemPosition]

    }


}
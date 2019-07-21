package com.hiden.challenge.presentation.common.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hiden.challenge.presentation.common.viewholder.BindingViewHolder
import com.hiden.challenge.presentation.common.viewholder.ViewHolderFactory
import com.hiden.challenge.data.entity.MovieResponse
import javax.inject.Inject

class SearchMovieAdapter @Inject constructor(
    private val viewHolderFactory: ViewHolderFactory<BindingViewHolder<MovieResponse>>
) : RecyclerView.Adapter<BindingViewHolder<MovieResponse>>() {

    interface Delegate {
        fun onMovieItemClicked(movieResponse: MovieResponse)
    }

    private val items = mutableListOf<MovieResponse>()

    fun setItems(newItems: List<MovieResponse>) {
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

    override fun onBindViewHolder(holder: BindingViewHolder<MovieResponse>, position: Int) =
        holder.bind(items[position])


    class DiffCallback(
        private val oldList: List<MovieResponse>,
        private val newList: List<MovieResponse>
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
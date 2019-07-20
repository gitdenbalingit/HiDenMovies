package com.hiden.movies.presentation.common.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hiden.movies.data.entity.GameResultResponse
import com.hiden.movies.presentation.common.viewholder.BindingViewHolder
import com.hiden.movies.presentation.common.viewholder.ViewHolderFactory
import javax.inject.Inject

class GamesAdapter @Inject constructor(
        private val viewHolderFactory: ViewHolderFactory<BindingViewHolder<GameResultResponse>>
) : RecyclerView.Adapter<BindingViewHolder<GameResultResponse>>() {

    interface Delegate {
        fun onGameItemClicked(gameResultResponse: GameResultResponse)
    }

    private val items = mutableListOf<GameResultResponse>()

    fun setItems(newItems: List<GameResultResponse>) {
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

    override fun onBindViewHolder(holder: BindingViewHolder<GameResultResponse>, position: Int) =
            holder.bind(items[position])


    class DiffCallback(
            private val oldList: List<GameResultResponse>,
            private val newList: List<GameResultResponse>
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
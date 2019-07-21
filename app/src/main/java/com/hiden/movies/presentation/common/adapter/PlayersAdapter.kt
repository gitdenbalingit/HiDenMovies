package com.hiden.movies.presentation.common.adapter

import android.view.ViewGroup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hiden.movies.data.entity.PlayerResponse
import com.hiden.movies.presentation.common.viewholder.SelectionBindingViewHolder
import com.hiden.movies.presentation.common.viewholder.ViewHolderFactory

import javax.inject.Inject

class PlayersAdapter @Inject constructor(
        private val viewHolderFactory: ViewHolderFactory<SelectionBindingViewHolder<PlayerResponse>>
) : RecyclerView.Adapter<SelectionBindingViewHolder<PlayerResponse>>() {

    init {
        setHasStableIds(true)
    }

    val items = mutableListOf<PlayerResponse>()
    var tracker: SelectionTracker<Long>? = null

    fun setItems(newItems: List<PlayerResponse>) {
        val result = DiffUtil.calculateDiff(
                DiffCallback(items, newItems)
        )

        items.clear()
        items.addAll(newItems)
        result.dispatchUpdatesTo(this)
    }

    override fun getItemId(position: Int) = position.toLong()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = viewHolderFactory.create(parent)

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: SelectionBindingViewHolder<PlayerResponse>, position: Int)  {
        val number = items[position]
        tracker?.let {
            holder.bind(number, it.isSelected(position.toLong()))
        }

    }

    class DiffCallback(
            private val oldList: List<PlayerResponse>,
            private val newList: List<PlayerResponse>
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
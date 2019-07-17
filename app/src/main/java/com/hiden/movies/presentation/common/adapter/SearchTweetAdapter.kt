package com.hiden.movies.presentation.common.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hiden.movies.presentation.common.viewholder.BindingViewHolder
import com.hiden.movies.presentation.common.viewholder.ViewHolderFactory
import com.hiden.movies.presentation.model.UserStatusDataView
import javax.inject.Inject

class SearchTweetAdapter @Inject constructor(
        private val viewHolderFactory: ViewHolderFactory<BindingViewHolder<UserStatusDataView>>
) : RecyclerView.Adapter<BindingViewHolder<UserStatusDataView>>() {

    interface Delegate {
        fun onStatusItemClicked(userStatusDataView: UserStatusDataView)
    }

    private val items = mutableListOf<UserStatusDataView>()

    fun setItems(newItems: List<UserStatusDataView>) {
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

    override fun onBindViewHolder(holder: BindingViewHolder<UserStatusDataView>, position: Int) =
            holder.bind(items[position])


    class DiffCallback(
            private val oldList: List<UserStatusDataView>,
            private val newList: List<UserStatusDataView>
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
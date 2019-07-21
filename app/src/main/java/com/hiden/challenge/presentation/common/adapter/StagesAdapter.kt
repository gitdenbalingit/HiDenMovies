package com.hiden.challenge.presentation.common.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hiden.challenge.data.entity.StagesResponse
import com.hiden.challenge.presentation.common.viewholder.BindingViewHolder
import com.hiden.challenge.presentation.common.viewholder.ViewHolderFactory
import javax.inject.Inject

class StagesAdapter @Inject constructor(
        private val viewHolderFactory: ViewHolderFactory<BindingViewHolder<StagesResponse>>
) : RecyclerView.Adapter<BindingViewHolder<StagesResponse>>() {

    interface Delegate {
        fun onStageItemClicked(stagesResponse: StagesResponse)
    }

    private val items = mutableListOf<StagesResponse>()

    fun setItems(newItems: List<StagesResponse>) {
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

    override fun onBindViewHolder(holder: BindingViewHolder<StagesResponse>, position: Int) =
            holder.bind(items[position])


    class DiffCallback(
            private val oldList: List<StagesResponse>,
            private val newList: List<StagesResponse>
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
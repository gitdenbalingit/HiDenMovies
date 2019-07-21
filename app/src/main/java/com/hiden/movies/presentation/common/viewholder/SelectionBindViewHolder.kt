package com.hiden.movies.presentation.common.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class SelectionBindingViewHolder<Item>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: Item, isActivated: Boolean = false)
}
package com.hiden.movies.presentation.ui.chooseplayers

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView
import com.hiden.movies.presentation.common.viewholder.PlayersItemViewHolder

class PlayerItemDetailsLookUp(private val recyclerView: RecyclerView) :
        ItemDetailsLookup<Long>() {
    override fun getItemDetails(event: MotionEvent): ItemDetails<Long>? {
        val view = recyclerView.findChildViewUnder(event.x, event.y)
        if (view != null) {
            return (recyclerView.getChildViewHolder(view) as PlayersItemViewHolder)
                    .getItemDetails()
        }
        return null
    }
}
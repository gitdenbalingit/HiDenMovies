package com.hiden.movies.presentation.common.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hiden.movies.R
import com.hiden.movies.data.entity.StagesResponse
import com.hiden.movies.presentation.common.adapter.StagesAdapter
import kotlinx.android.synthetic.main.item_stage_view.view.*

class StagesItemViewHolder(
        itemView: View,
        private val callback: StagesAdapter.Delegate
) : BindingViewHolder<StagesResponse>(itemView) {



    private var stagesResponse: StagesResponse? = null

    class Factory(private val delegate: StagesAdapter.Delegate) : ViewHolderFactory<BindingViewHolder<StagesResponse>> {
        override fun create(parent: ViewGroup) = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_stage_view, parent, false)
                .let { StagesItemViewHolder(it, delegate)}
    }

    init {
        itemView.setOnClickListener {
            stagesResponse?.let { callback.onStageItemClicked(it) }
        }
    }



    override fun bind(item: StagesResponse) {
        this.stagesResponse = item
        itemView.stage_title.text = item.name

//        GlideApp.with(itemView)
//                .load(item.image)
//                .centerCrop()
//                .into(itemView.game_image)

    }

}
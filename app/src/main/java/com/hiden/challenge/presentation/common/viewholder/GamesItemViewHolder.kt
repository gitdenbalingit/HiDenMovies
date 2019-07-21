package com.hiden.challenge.presentation.common.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hiden.challenge.R
import com.hiden.challenge.data.di.GlideApp
import com.hiden.challenge.data.entity.GameResultResponse
import com.hiden.challenge.presentation.common.adapter.GamesAdapter
import kotlinx.android.synthetic.main.item_game_view.view.*

class GamesItemViewHolder(
        itemView: View,
        private val callback: GamesAdapter.Delegate
) : BindingViewHolder<GameResultResponse>(itemView) {



    private var gameResultResponse: GameResultResponse? = null

    class Factory(private val delegate: GamesAdapter.Delegate) : ViewHolderFactory<BindingViewHolder<GameResultResponse>> {
        override fun create(parent: ViewGroup) = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_game_view, parent, false)
                .let { GamesItemViewHolder(it, delegate)}
    }

    init {
        itemView.setOnClickListener {
            gameResultResponse?.let { callback.onGameItemClicked(it) }
        }
    }



    override fun bind(item: GameResultResponse) {
        this.gameResultResponse = item
        itemView.game_title.text = item.name

        GlideApp.with(itemView)
                .load(item.image)
                .centerCrop()
                .into(itemView.game_image)

    }

}
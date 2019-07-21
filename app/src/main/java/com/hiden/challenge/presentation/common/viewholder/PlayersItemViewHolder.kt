package com.hiden.challenge.presentation.common.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.selection.ItemDetailsLookup
import com.hiden.challenge.R
import com.hiden.challenge.data.entity.PlayerResponse
import kotlinx.android.synthetic.main.item_players_view.view.*
import org.jetbrains.anko.imageResource

class PlayersItemViewHolder(
        itemView: View
) : SelectionBindingViewHolder<PlayerResponse>(itemView) {


    private var playerResponse: PlayerResponse? = null

    class Factory : ViewHolderFactory<SelectionBindingViewHolder<PlayerResponse>> {
        override fun create(parent: ViewGroup) = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_players_view, parent, false)
                .let { PlayersItemViewHolder(it) }
    }



    override fun bind(item: PlayerResponse, isActivated: Boolean) {
        this.playerResponse = item
        itemView.isActivated = isActivated

        val name = item.profile.firstName + " "  +item.profile.lastName

        itemView.player_status.imageResource = R.drawable.ic_add_circle_gray_24dp.takeUnless { isActivated }?: R.drawable.ic_remove_circle_blue_24dp
        itemView.player_name.text  = name


//        if(isActivated){
//            itemView.check.visibility = View.VISIBLE
//            val animation = AnimationUtils.loadAnimation(itemView.check.context, R.anim.scale_in)
//            itemView.check.startAnimation(animation)
//        } else {
//            val animation = AnimationUtils.loadAnimation(itemView.check.context, R.anim.scale_out)
//            itemView.check.startAnimation(animation)
//            itemView.check.visibility = View.INVISIBLE
//        }
//
//        itemView.contact_name.text = item.name
//        itemView.contact_smsnumber.text = item.phone
//        itemView.contact_avatar.text = item.name.getInitial()
    }

    fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int = adapterPosition
                override fun getSelectionKey(): Long? = itemId
            }

}
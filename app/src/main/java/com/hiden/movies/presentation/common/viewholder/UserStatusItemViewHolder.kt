package com.hiden.movies.presentation.common.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hiden.movies.R
import com.hiden.movies.data.di.GlideApp
import com.hiden.movies.presentation.common.adapter.UserStatusAdapter
import com.hiden.movies.presentation.model.UserStatusDataView
import kotlinx.android.synthetic.main.item_user_status_view.view.*

class UserStatusItemViewHolder(
        itemView: View,
        private val callback: UserStatusAdapter.Delegate
) : BindingViewHolder<UserStatusDataView>(itemView) {



    private var userStatusDataView: UserStatusDataView? = null

    class Factory(private val delegate: UserStatusAdapter.Delegate) :
            ViewHolderFactory<BindingViewHolder<UserStatusDataView>> {
        override fun create(parent: ViewGroup) = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_user_status_view, parent, false)
                .let { UserStatusItemViewHolder(it, delegate)}
    }

    init {

        itemView.setOnClickListener {
            userStatusDataView?.let { callback.onStatusItemClicked(it) }
        }
    }




    override fun bind(item: UserStatusDataView) {
        this.userStatusDataView = item

        itemView.user_name.text = "User name:"+item.user_name
        itemView.user_post.text = "Status:"+item.text
        itemView.retweeted.text = "Retweeted: Yes".takeIf { item.retweeted } ?: "Retweeted: No"
        itemView.quote.text = "Quote status: Yes".takeIf { item.is_quote_status } ?: "Quote status: No"
        itemView.date.text = item.created_at


    }

}
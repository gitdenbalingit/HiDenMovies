package com.hiden.movies.presentation.common.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hiden.movies.R
import com.hiden.movies.presentation.common.adapter.SearchTweetAdapter
import com.hiden.movies.presentation.model.UserStatusDataView
import kotlinx.android.synthetic.main.item_user_status_view.view.*

class SearchTweetItemViewHolder(
        itemView: View,
        private val callback: SearchTweetAdapter.Delegate
) : BindingViewHolder<UserStatusDataView>(itemView) {



    private var userStatusDataView: UserStatusDataView? = null

    class Factory(private val delegate: SearchTweetAdapter.Delegate) :
            ViewHolderFactory<BindingViewHolder<UserStatusDataView>> {
        override fun create(parent: ViewGroup) = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_user_status_view, parent, false)
                .let { SearchTweetItemViewHolder(it, delegate)}
    }

    init {

        userStatusDataView?.let { callback.onStatusItemClicked(it) }
    }




    override fun bind(item: UserStatusDataView) {
        this.userStatusDataView = item

        itemView.user_name.text = "User name:"+item.user_name
        itemView.user_post.text = "Status:"+item.text
        itemView.retweeted.text = "Retweeted: Yes".takeIf { item.retweeted } ?: "Retweeted: No"
        itemView.quote.text = "Quote status: Yes".takeIf { item.is_quote_status } ?: "Quote status: No"
        itemView.date.text = item.created_at


//        GlideApp.with(itemView)
//                .load(IMAGE_BASE_URL_154 + item.posterPath)
////            .placeholder(R.drawable.bg_rounded_corner_2dp)
////            .error(R.drawable.ic_broken_image)
//                .into(itemView.movie_image)

    }

}
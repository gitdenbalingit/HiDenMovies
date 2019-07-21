package com.hiden.challenge.presentation.common.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hiden.challenge.data.entity.MovieResponse
import com.hiden.challenge.presentation.common.viewholder.SearchItemWithPageViewHolder
import com.hiden.challenge.presentation.model.State

class SearchMoviePagedAdapter(
    private val retry: () -> Unit,
    val clickListener: (MovieResponse) -> Unit
) : PagedListAdapter<MovieResponse, RecyclerView.ViewHolder>(DiffCallback) {

    private val DATA_VIEW_ODD = 1
    private val DATA_VIEW_EVEN = 2
    private val FOOTER_VIEW_TYPE = 3

    private var state = State.LOADING

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SearchItemWithPageViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as SearchItemWithPageViewHolder).bind(getItem(position), clickListener)
    }


    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<MovieResponse>() {
            override fun areItemsTheSame(oldItem: MovieResponse, newItem: MovieResponse): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieResponse, newItem: MovieResponse): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1 else 0
    }

    private fun hasFooter(): Boolean {
        return super.getItemCount() != 0 && (state == State.LOADING || state == State.ERROR)
    }

    fun setState(state: State) {
        this.state = state
        notifyItemChanged(super.getItemCount())
    }
}
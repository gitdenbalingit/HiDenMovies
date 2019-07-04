package com.hiden.movies.presentation.common.viewholder

import android.view.ViewGroup

interface ViewHolderFactory<ViewHolder> {
  fun create(parent: ViewGroup): ViewHolder
}
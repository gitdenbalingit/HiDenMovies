package com.hiden.challenge.presentation.common.viewholder

import android.view.ViewGroup

interface ViewHolderFactory<ViewHolder> {
  fun create(parent: ViewGroup): ViewHolder
}
package com.hiden.movies.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CarBuiltDate(val key: String,
                        val builtDate : String) : Parcelable

package com.hiden.challenge.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CarMainType(val key: String,
                       val mainType : String) : Parcelable

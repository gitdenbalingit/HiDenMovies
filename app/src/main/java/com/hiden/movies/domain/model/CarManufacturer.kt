package com.hiden.movies.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CarManufacturer(val key: String,
                           val manufacturer: String) : Parcelable

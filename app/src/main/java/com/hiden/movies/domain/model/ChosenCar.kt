package com.hiden.movies.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChosenCar(
    var id: String,
    var manufacturer_key: String,
    var manufacturer: String,
    var mainType_key: String,
    var mainType: String,
    var builtDate: String,
    var builtDate_key: String) : Parcelable





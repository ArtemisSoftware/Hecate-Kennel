package com.artemissoftware.presentation.details

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class DogDetail(
    val name: String,
    val breedGroup: String,
    val temperament: String,
    val imageUrl: String
) :Parcelable
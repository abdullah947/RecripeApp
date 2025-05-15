package com.smarthealth.shared.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class GridDish(
    val id: String,
    val title: String,
    val imageUrl: String,
    var instructions: String,
    var ingredientsList: String
) : Parcelable
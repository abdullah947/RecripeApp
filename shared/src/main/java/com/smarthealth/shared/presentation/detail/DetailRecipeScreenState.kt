package com.smarthealth.shared.presentation.detail

data class DetailRecipeScreenState(
    val title: String = "",
    val imageUrl: String = "",
    val isLoading: Boolean = true,
    var isFavourite: Boolean = false,
    val instructions: String = "",
    val ingredients: String = "",
    val txtImgLoadFail: String = "Failed to load image",
    val btnModifyText: String = "Modify Recipe",
    val btnFavouriteText: String = "Like"

)

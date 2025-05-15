package com.smarthealth.local.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.smarthealth.local.domain.models.RecipeHistory

@Entity(tableName = "Recipes_table")
data class RecipeHistoryDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val dishId: String,
    val title: String,
    val imageUrl: String,
    val instructions: String,
    val ingredientsList: String
)

fun RecipeHistoryDTO.toDomain(): RecipeHistory{
    return RecipeHistory(
        id = id,
        dishId = dishId,
        title = title,
        imageUrl = imageUrl,
        instructions = instructions,
        ingredientsList = ingredientsList
    )
}

fun RecipeHistory.toEntity():RecipeHistoryDTO{
    return RecipeHistoryDTO(
        id= id,
        dishId = dishId,
        title = title,
        imageUrl = imageUrl,
        instructions = instructions,
        ingredientsList = ingredientsList
    )
}
package com.smarthealth.shared.presentation.detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smarthealth.network.utils.ApiKeys
import com.smarthealth.network.utils.NetworkResult
import com.smarthealth.shared.data.api.models.recipebyid.toDomain
import com.smarthealth.shared.data.models.GridDish
import com.smarthealth.shared.domain.repo.DetailRecipeRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class DetailRecipeViewModel(
    private val repository: DetailRecipeRepo,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var state by mutableStateOf(DetailRecipeScreenState())
        private set

    private val dishJson = savedStateHandle.get<String>("data")
    private val dish = Json.decodeFromString<GridDish>(dishJson!!)

    init {
        if (dish.instructions == "" && dish.ingredientsList == "") {
                getMakeRecipeDetail(dish)

        } else {
            Log.d("instr", "ingredients:${dish.ingredientsList} ")
            Log.d("instr", "ingredients:${dish.instructions} ")
            getSearchRecipeDetails(dish)
        }
    }

    private fun getSearchRecipeDetails(dish: GridDish) {
        state = state.copy(
            isLoading = false,
            title = dish.title,
            imageUrl = dish.imageUrl,
            ingredients = dish.ingredientsList,
            instructions = dish.instructions
        )
    }

    private fun getMakeRecipeDetail(dish: GridDish) {

        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getRandomMealsById(dish.id, ApiKeys.SPOON_API_KEY)
            state = when (result) {
                is NetworkResult.Success -> {

                    val recipe = result.data?.map { it.toDomain() } ?: emptyList()

                    if (recipe.isNotEmpty()) {
                        val allSteps = recipe.flatMap { it.steps }
                        val instructionsText = allSteps.joinToString("\n") { it.step }
                        val ingredientsList = allSteps
                            .flatMap { it.ingredients }
                            .map { it.name }
                            .distinct()
                            .joinToString(",")

                        state.copy(
                            isLoading = false,
                            title = dish.title,
                            imageUrl = dish.imageUrl,
                            instructions = instructionsText,
                            ingredients = ingredientsList
                        )
                    } else {
                        state.copy(
                            isLoading = false,
                        )
                    }
                }

                is NetworkResult.Error -> {
                    state.copy(isLoading = false)
                }

                is NetworkResult.Loading -> {
                    state.copy(isLoading = true)
                }
            }
        }
    }
}
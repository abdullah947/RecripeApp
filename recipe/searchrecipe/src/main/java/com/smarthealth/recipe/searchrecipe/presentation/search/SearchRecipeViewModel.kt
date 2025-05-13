package com.smarthealth.recipe.searchrecipe.presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smarthealth.network.utils.NetworkResult
import com.smarthealth.recipe.searchrecipe.domain.repo.SearchRecipeRepo
import com.smarthealth.network.utils.ApiKeys
import com.smarthealth.recipe.searchrecipe.data.api.models.searchrecipes.toDomain
import com.smarthealth.recipe.searchrecipe.data.api.models.randomrecipes.toDomain
import com.smarthealth.shared.data.models.GridDish
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchRecipeViewModel(private val repository: SearchRecipeRepo) : ViewModel() {

    var state by mutableStateOf(SearchRecipeScreenState())
        private set

    init {
        getRandomRecipeData()
    }

    fun onEvent(actionEvents: ActionEvent) = viewModelScope.launch {
        when (actionEvents) {
            is ActionEvent.OnTextChange -> {
                state = state.copy(textSearch = actionEvents.text)
            }
        }
    }

     fun getSearchRecipeData() {
         viewModelScope.launch(Dispatchers.IO) {
             val query = state.textSearch.text
             val result = repository.getMeals(query)

             state = when (result) {
                 is NetworkResult.Success -> {
                     val domainMeals = result.data?.toDomain()?.meals ?: emptyList()
                     val gridItems = domainMeals.map { meal ->
                         val ingredientsList = listOfNotNull(
                             meal.strIngredient1,
                             meal.strIngredient2,
                             meal.strIngredient3,
                             meal.strIngredient4,
                             meal.strIngredient5,
                             meal.strIngredient6,
                             meal.strIngredient7,
                             meal.strIngredient8,
                             meal.strIngredient9,
                             meal.strIngredient10,
                             meal.strIngredient11,
                             meal.strIngredient12,
                             meal.strIngredient13,
                             meal.strIngredient14,
                             meal.strIngredient15,
                             meal.strIngredient16,
                             meal.strIngredient17,
                             meal.strIngredient18,
                             meal.strIngredient19,
                             meal.strIngredient20
                         ).filter { it.isNotBlank() }

                         GridDish(
                             id = meal.idMeal,
                             title = meal.strMeal,
                             imageUrl = meal.strMealThumb,
                             instructions = meal.strInstructions,
                             ingredientsList = ingredientsList.joinToString(",")
                         )
                     }

                     if (gridItems.isEmpty()) {
                         state.copy(
                             isSuccess = false,
                             isLoading = false,
                             recipeList = emptyList()
                         )
                     } else {
                         state.copy(
                             isSuccess = true,
                             isLoading = false,
                             recipeList = gridItems
                         )
                     }
                 }

                 is NetworkResult.Error -> {
                     state.copy(isSuccess = false, isLoading = false, recipeList = emptyList())
                 }

                 is NetworkResult.Loading -> {
                     state.copy(isLoading = true)
                 }
             }
         }

    }

    private fun getRandomRecipeData() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getRandomMeals(10, ApiKeys.SPOON_API)

            state = when (result) {
                is NetworkResult.Success -> {
                    val domainRecipes = result.data?.toDomain()?.recipes ?: emptyList()


                    val gridItems = domainRecipes.map { recipe ->

                        val instructions = recipe.analyzedInstructions
                            .flatMap { it.steps }
                            .joinToString("\n") { step -> "${step.number}. ${step.step}" }

                        val ingredientsList = recipe.analyzedInstructions
                            .asSequence()
                            .flatMap { it.steps }
                            .flatMap { it.ingredients }
                            .map { it.name }
                            .distinct()
                            .joinToString(",")

                        GridDish(
                            id = recipe.id,
                            title = recipe.title,
                            imageUrl = recipe.image,
                            instructions = instructions,
                            ingredientsList = ingredientsList
                        )
                    }

                    state.copy(
                        isSuccess = domainRecipes.isNotEmpty(),
                        isLoading = false,
                        recipeList = gridItems
                    )
                }

                is NetworkResult.Error -> {
                    state.copy(isSuccess = false, isLoading = false, recipeList = emptyList())
                }

                is NetworkResult.Loading -> {
                    state.copy(isLoading = true)
                }
            }
        }
    }

    sealed class ActionEvent {
        data class OnTextChange(val text: TextFieldValue) : ActionEvent()
    }
}
package com.smarthealth.recipe.searchrecipe.presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smarthealth.local.domain.models.SearchHistory
import com.smarthealth.local.domain.repo.RecipeHistoryRepo
import com.smarthealth.local.domain.repo.SearchHistoryRepo
import com.smarthealth.network.utils.ApiKeys
import com.smarthealth.network.utils.NetworkResult
import com.smarthealth.recipe.searchrecipe.domain.repo.SearchRecipeRepo
import com.smarthealth.shared.data.models.GridDish
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SearchRecipeViewModel(
    private val searchRepo: SearchRecipeRepo,
    recipeHistoryRepo: RecipeHistoryRepo,
    private val searchHistoryRepo: SearchHistoryRepo,
) : ViewModel() {

    var state by mutableStateOf(SearchRecipeScreenState())
        private set

    private val _navigationEvent = MutableSharedFlow<NavigationEvent>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    val recipes = recipeHistoryRepo.getAllRecipes()

    init {
        getRandomRecipeData()
    }

    fun onEvent(actionEvents: ActionEvent) = viewModelScope.launch {
        when (actionEvents) {
            is ActionEvent.OnTextChange -> {
                val query = actionEvents.text.text

                val suggestions = if (query.isNotBlank()) {
                    searchHistoryRepo.getSuggestions(query)
                } else emptyList()

                state = state.copy(
                    textSearch = actionEvents.text,
                    searchSuggestions = suggestions
                )
            }
            is ActionEvent.OnItemClick -> {
                onItemClicked(actionEvents.dish)
            }
            is ActionEvent.OnSearchClick -> {
                getSearchRecipeData()
            }
        }
    }

    private fun getSearchRecipeData() {
        viewModelScope.launch(Dispatchers.IO) {
            if (state.textSearch.text.isEmpty()) {
                viewModelScope.launch(Dispatchers.IO) {
                    _uiEvent.emit(UiEvent.ShowToast("Please write something"))

                }
            } else {
                _uiEvent.emit(UiEvent.HideKeyboard)
                state = state.copy(searchSuggestions = emptyList())
                val query = state.textSearch.text
                insertSearchToDb(query)
                val result = searchRepo.getMeals(query)

                state = when (result) {
                    is NetworkResult.Success -> {
                        val domainMeals = result.data?.meals ?: emptyList()
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
                                recipeList = emptyList(),
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
    }

    private fun getRandomRecipeData() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = searchRepo.getRandomMeals(50, ApiKeys.SPOON_API)
            state = when (result) {
                is NetworkResult.Success -> {
                    val domainRecipes = result.data?.recipes ?: emptyList()
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

    private fun insertSearchToDb(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val trimmedQuery = query.trim()
            val allSearches = searchHistoryRepo.getAllSearches()
            val isDuplicate = allSearches.any {
                it.searchText.equals(trimmedQuery, ignoreCase = true)
            }
            if (!isDuplicate) {
                val search = SearchHistory(searchText = trimmedQuery)
                searchHistoryRepo.insertSearch(search)
            }
        }
    }

    sealed class ActionEvent {
        data class OnTextChange(val text: TextFieldValue) : ActionEvent()
        data class OnItemClick(val dish: GridDish) : ActionEvent()
        data object OnSearchClick : ActionEvent()
    }

    sealed class NavigationEvent {
        data class ToDetailScreen(val dish: GridDish) : NavigationEvent()
    }

    sealed class UiEvent {
        data object HideKeyboard : UiEvent()
        data class ShowToast(val message: String) : UiEvent()
    }

    private fun onItemClicked(dish: GridDish) {
        viewModelScope.launch {
            _navigationEvent.emit(NavigationEvent.ToDetailScreen(dish))
        }
    }
}
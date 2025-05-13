package com.smarthealth.recipe.makerecipe.presentation.make

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smarthealth.network.utils.ApiKeys
import com.smarthealth.network.utils.NetworkResult
import com.smarthealth.recipe.makerecipe.R
import com.smarthealth.recipe.makerecipe.data.api.models.makerecipes.toDomain
import com.smarthealth.recipe.makerecipe.data.models.IngredientsListItem
import com.smarthealth.recipe.makerecipe.domain.repo.MakeRecipeRepo
import com.smarthealth.shared.data.models.GridDish
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MakeRecipeViewModel(private val repository: MakeRecipeRepo) : ViewModel() {

    var state by mutableStateOf(MakeRecipeScreenState())
        private set

    init {
        loadIngredients()
    }

    fun onEvent(actionEvents: ActionEvent) {
        state = when (actionEvents) {
            is ActionEvent.OnTextChange -> {
                state.copy(textSearch = actionEvents.text)
            }

            is ActionEvent.OnIngredientCheckedChange -> {
                val updatedIngredients = state.ingredientsList.map { ingredient ->
                    if (ingredient.text == actionEvents.ingredientName) {
                        ingredient.copy(isChecked = actionEvents.isChecked)
                    } else {
                        ingredient
                    }
                }

                val selectedIngredients = updatedIngredients
                    .filter { it.isChecked }
                    .joinToString(",") { it.text }

                state.copy(
                    ingredientsList = updatedIngredients,
                    textSearch = TextFieldValue(selectedIngredients)
                )
            }
        }
    }

    fun getRecipeData() {

        viewModelScope.launch(Dispatchers.IO) {

            state = state.copy(isMakeBtnClicked = true)

            val query = state.textSearch.text
            val result = repository.getMealsByIngredients(query, ApiKeys.SPOON_API)

            state = when (result) {
                is NetworkResult.Success -> {
                    val domainRecipes = result.data?.map { it.toDomain() } ?: emptyList()

                    if (domainRecipes.isNotEmpty()) {
                        val gridItems = domainRecipes.map { dish ->
                            GridDish(
                                id = dish.id,
                                title = dish.title,
                                imageUrl = dish.image,
                                instructions = "",
                                ingredientsList = ""
                            )
                        }
                        state.copy(
                            isSuccess = true,
                            isLoading = false,
                            recipeList = gridItems
                        )
                    } else {
                        state.copy(
                            isSuccess = false,
                            isLoading = false,
                            recipeList = emptyList()
                        )
                    }
                }

                is NetworkResult.Error -> {
                    Log.d("RecipeFetcher", "Request failed: ${result.message}")
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
        data class OnIngredientCheckedChange(val ingredientName: String, val isChecked: Boolean) : ActionEvent()
    }

    private fun loadIngredients() {
        val ingredients = listOf(
            IngredientsListItem("Onion", R.drawable.img_onion),
            IngredientsListItem("Tomato", R.drawable.img_tomato),
            IngredientsListItem("Garlic", R.drawable.img_garlic),
            IngredientsListItem("Ginger", R.drawable.img_ginger),
            IngredientsListItem("Green Chili", R.drawable.img_green_chili),
            IngredientsListItem("Coriander Leaves", R.drawable.img_corainder_leaves),
            IngredientsListItem("Mint Leaves", R.drawable.img_mint_leaves),
            IngredientsListItem("Potato", R.drawable.img_potato),
            IngredientsListItem("Lemon", R.drawable.img_lemon),
            IngredientsListItem("Olives", R.drawable.img_olives),
            IngredientsListItem("Salt", R.drawable.img_salt),
            IngredientsListItem("Red Chili Powder", R.drawable.img_red_chili_powder),
            IngredientsListItem("Turmeric Powder", R.drawable.img_turmeric_powder),
            IngredientsListItem("Black Pepper Powder ", R.drawable.img_black_pepper_powder),
            IngredientsListItem("Cumin Seeds", R.drawable.img_cumin_seed),
            IngredientsListItem("Black Pepper", R.drawable.img_black_pepper),
            IngredientsListItem("Yogurt", R.drawable.img_yogurt),
            IngredientsListItem("Milk", R.drawable.img_milk),
            IngredientsListItem("Cream", R.drawable.img_cream),
            IngredientsListItem("Cheese", R.drawable.img_cheese),
            IngredientsListItem("Oil", R.drawable.img_oil),
            IngredientsListItem("Honey", R.drawable.img_honey),
            IngredientsListItem("Rice", R.drawable.img_rice),
            IngredientsListItem("Flour", R.drawable.img_flour),
            IngredientsListItem("Chicken", R.drawable.img_chicken),
            IngredientsListItem("Meat", R.drawable.img_meat),
            IngredientsListItem("Fish Meat", R.drawable.img_fish)
        )
        state = state.copy(ingredientsList = ingredients)
    }
}


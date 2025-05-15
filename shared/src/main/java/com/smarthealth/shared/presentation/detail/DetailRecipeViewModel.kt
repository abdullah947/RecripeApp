package com.smarthealth.shared.presentation.detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.smarthealth.local.data.db.models.RecipeHistoryDTO
import com.smarthealth.local.domain.repo.RecipeHistoryRepo
import com.smarthealth.network.utils.ApiKeys
import com.smarthealth.network.utils.NetworkResult
import com.smarthealth.shared.data.api.models.recipebyid.toDomain
import com.smarthealth.shared.data.models.GridDish
import com.smarthealth.shared.domain.repo.DetailRecipeRepo
import com.smarthealth.shared.navigation.RecipeDetailScreens
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class DetailRecipeViewModel(
    private val detailRepo: DetailRecipeRepo,
    private val recipesRepo: RecipeHistoryRepo,
    savedStateHandle: SavedStateHandle,

    ): ViewModel() {

    var state by mutableStateOf(DetailRecipeScreenState())
        private set

    private val dishItem = savedStateHandle.toRoute<RecipeDetailScreens.DetailScreen>()
    val gridDish = GridDish(
        id = dishItem.id,
        title = dishItem.title,
        imageUrl = dishItem.imageUrl,
        instructions = dishItem.instructions,
        ingredientsList = dishItem.ingredientsList
    )
//    private val dishItem = Json.decodeFromString<GridDish>(dishJson!!)

    init {


        if (dishItem.instructions == "" && dishItem.ingredientsList == "") {
                getMakeRecipeDetail(gridDish)

        } else {
            Log.d("instr", "ingredients:${dishItem.ingredientsList} ")
            Log.d("instr", "ingredients:${dishItem.instructions} ")
            getSearchRecipeDetails(gridDish)
        }
    }

    private fun getSearchRecipeDetails(dish: GridDish) {

        viewModelScope.launch(Dispatchers.IO) {
            val isFav = checkIfFavourite(dish.id)
            updateFavouriteBtnText(isFav)
            state = state.copy(
                isLoading = false,
                title = dish.title,
                imageUrl = dish.imageUrl,
                ingredients = dish.ingredientsList,
                instructions = dish.instructions,
                isFavourite = isFav
            )
        }

    }

    private fun getMakeRecipeDetail(dish: GridDish) {

        viewModelScope.launch(Dispatchers.IO) {
            val result = detailRepo.getRandomMealsById(dish.id, ApiKeys.SPOON_API)
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



                        val dish = GridDish(
                            id = dish.id,
                            title = dish.title,
                            imageUrl = dish.imageUrl,
                            instructions = instructionsText,
                            ingredientsList = ingredientsList
                        )
                        if (dishItem.ingredientsList == "" && dishItem.instructions == ""){
                            dishItem.ingredientsList = ingredientsList
                            dishItem.instructions = instructionsText
                        }

                        val isFav = checkIfFavourite(dish.id)
                        updateFavouriteBtnText(isFav)

                        state.copy(
                            isLoading = false,
                            title = dish.title,
                            imageUrl = dish.imageUrl,
                            instructions = instructionsText,
                            ingredients = ingredientsList,
                            isFavourite = isFav

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




    private fun insertRecipeToDb(dish: GridDish) {
        viewModelScope.launch(Dispatchers.IO) {
            val recipe = RecipeHistoryDTO(
                dishId = dish.id,
                title = dish.title,
                imageUrl = dish.imageUrl,
                instructions = dish.instructions,
                ingredientsList = dish.ingredientsList
            )
            recipesRepo.insertRecipe(recipe)
        }
    }
    private fun deleteRecipeToDb(dish: GridDish) {
        viewModelScope.launch(Dispatchers.IO) {
            recipesRepo.deleteSpecificRecipe(dish.id)
        }
    }
     fun onBtnFavouriteClick(){
        state.isFavourite = !state.isFavourite
        if (state.isFavourite){
            insertRecipeToDb(gridDish)
            updateFavouriteBtnText(state.isFavourite)
        }else{
            deleteRecipeToDb(gridDish)
            updateFavouriteBtnText(state.isFavourite)
        }
    }
    private fun updateFavouriteBtnText(isFav: Boolean) {
        val newText = if (isFav) "Dislike" else "Like"
        state = state.copy(btnFavouriteText = newText)
    }
    private suspend fun checkIfFavourite(id:String): Boolean {
        return withContext(Dispatchers.IO) {
            val recipe = recipesRepo.getSpecificRecipe(id)
            recipe != null
        }
    }
}
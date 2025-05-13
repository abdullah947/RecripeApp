package com.smarthealth.recipe.modifyrecipe.presentation.modify

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smarthealth.network.utils.ApiKeys
import com.smarthealth.network.utils.NetworkResult
import com.smarthealth.recipe.modifyrecipe.data.api.models.response.ContentDTO
import com.smarthealth.recipe.modifyrecipe.data.api.models.response.PartDTO
import com.smarthealth.recipe.modifyrecipe.data.api.models.request.GeminiRequest
import com.smarthealth.recipe.modifyrecipe.data.api.models.response.toDomain
import com.smarthealth.recipe.modifyrecipe.domain.models.Content
import com.smarthealth.recipe.modifyrecipe.domain.models.Part
import com.smarthealth.recipe.modifyrecipe.domain.repo.ModifyRecipeRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ModifyRecipeViewModel(private val repository: ModifyRecipeRepo) : ViewModel() {

    var state by mutableStateOf(ModifyRecipeScreenState())
        private set


    fun onEvent(actionEvents: ActionEvent) = viewModelScope.launch {
        when (actionEvents) {
            is ActionEvent.OnTextChange -> {
                state = state.copy(textRequest = actionEvents.text)
            }
        }
    }

    fun chatWithAi() {
        viewModelScope.launch(Dispatchers.IO) {
            val query = state.textRequest.text
            val userContent = Content(
                parts = listOf(Part(query)),
                role = "user"
            )
            state = state.copy(
                msgList = state.msgList + userContent,
                isLoading = true,
                isSuccess = false
            )
            clearText()
            val request = GeminiRequest(contents = listOf(ContentDTO(parts = listOf(PartDTO(query)), role = "user")))

            val result = repository.chatWithAi(request, ApiKeys.GEMINI_API)

            state = when (result) {

                is NetworkResult.Success -> {
                    val responseMessages = result.data
                        ?.toDomain()
                        ?.candidates
                        ?.map { it.content } ?: emptyList()

                    state.copy(
                        isSuccess = true,
                        isLoading = false,
                        msgList = state.msgList + responseMessages
                    )
                }

                is NetworkResult.Error -> {
                    Log.d("resultAi", "chatWithAi:Error${result.message} ")
                    state.copy(
                        isSuccess = false,
                        isLoading = false,)
                }

                is NetworkResult.Loading -> {
                    Log.d("resultAi", "chatWithAi:Loading ")
                    state.copy(
                        isLoading = true,
                        isSuccess = false)

                }

            }
        }
    }

    private fun clearText(){
        state = state.copy(textRequest = TextFieldValue(""))
    }

    sealed class ActionEvent {
        data class OnTextChange(val text: TextFieldValue) : ActionEvent()
    }
}


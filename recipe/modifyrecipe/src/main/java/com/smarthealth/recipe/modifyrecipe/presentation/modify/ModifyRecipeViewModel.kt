package com.smarthealth.recipe.modifyrecipe.presentation.modify

import android.content.ClipboardManager
import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smarthealth.network.utils.ApiKeys
import com.smarthealth.network.utils.NetworkResult
import com.smarthealth.recipe.modifyrecipe.data.api.models.request.GeminiRequest
import com.smarthealth.recipe.modifyrecipe.domain.models.Content
import com.smarthealth.recipe.modifyrecipe.domain.models.Part
import com.smarthealth.recipe.modifyrecipe.domain.repo.ModifyRecipeRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

class ModifyRecipeViewModel(private val repository: ModifyRecipeRepo, context: Context) :
    ViewModel() {

    var state by mutableStateOf(ModifyRecipeScreenState())
        private set

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    private val contextRef = WeakReference(context)

    init {
        pasteFromClipboard()
    }

    fun onEvent(actionEvents: ActionEvent) = viewModelScope.launch {
        when (actionEvents) {
            is ActionEvent.OnTextChange -> {
                state = state.copy(textRequest = actionEvents.text)

            }

            ActionEvent.OnBtnSendClick -> {
                chatWithAi()
            }
        }
    }

    private fun chatWithAi() {
        viewModelScope.launch(Dispatchers.IO) {
            if (state.textRequest.text.isBlank()) {
                viewModelScope.launch(Dispatchers.IO) {
                    _uiEvent.emit(UiEvent.ShowToast("Please Write Something"))
                }

            } else {
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
                val request = GeminiRequest(
                    contents = listOf(
                        Content(
                            parts = listOf(Part(query)),
                            role = "user"
                        )
                    )
                )

                val result = repository.chatWithAi(request, ApiKeys.GEMINI_API)

                state = when (result) {

                    is NetworkResult.Success -> {
                        val responseMessages =
                            result.data?.candidates?.map { it.content } ?: emptyList()

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
                            isLoading = false,
                        )
                    }

                    is NetworkResult.Loading -> {
                        Log.d("resultAi", "chatWithAi:Loading ")
                        state.copy(
                            isLoading = true,
                            isSuccess = false
                        )
                    }
                }
            }
        }
    }

    private fun clearText() {
        state = state.copy(textRequest = TextFieldValue(""))
    }

    private fun pasteFromClipboard() {
        contextRef.get()?.let { ctx ->
            val clipboardManager =
                ctx.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

            if (clipboardManager.hasPrimaryClip() && (clipboardManager.primaryClip?.itemCount
                    ?: 0) > 0
            ) {
                val clipItem = clipboardManager.primaryClip?.getItemAt(0)
                val clipText = clipItem?.text?.toString() ?: ""

                if (clipText.isNotEmpty()) {
                    state = state.copy(
                        textRequest = TextFieldValue("I want to modify this recipe, let me share instructions with you\n$clipText")
                    )
                    Log.d("Clipboard", "Text pasted from clipboard: $clipText")
                }
            }
        }
    }

    sealed class ActionEvent {
        data class OnTextChange(val text: TextFieldValue) : ActionEvent()
        data object OnBtnSendClick : ActionEvent()
    }

    sealed class UiEvent {
        data class ShowToast(val message: String) : UiEvent()
    }
}


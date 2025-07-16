package com.smarthealth.recipe.modifyrecipe.presentation.modify

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.smarthealth.recipe.modifyrecipe.R
import com.smarthealth.recipe.modifyrecipe.presentation.components.ChatBar
import com.smarthealth.recipe.modifyrecipe.presentation.components.MsgBox
import com.smarthealth.shared.presentation.components.LottieLoader

@Composable
fun ModifyRecipeScreen(
    state: ModifyRecipeScreenState = ModifyRecipeScreenState(),
    actionEvent: (ModifyRecipeViewModel.ActionEvent) -> Unit = {},
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .windowInsetsPadding(WindowInsets.safeContent),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                reverseLayout = true
            ) {

                if (state.isLoading && !state.isSuccess) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            LottieLoader(R.raw.chat_loading, 100.dp)
                        }
                    }
                } else if (!state.isLoading && !state.isSuccess) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(text = stringResource(R.string.NetworkError))
                        }
                    }
                }

                items(state.msgList.reversed()) { message ->
                    message.parts.forEach { part ->
                        MsgBox(
                            text = part.text,
                            role = message.role
                        )
                    }
                }


            }
        }
        ChatBar(
            value = state.textRequest,
            onValueChange = { actionEvent(ModifyRecipeViewModel.ActionEvent.OnTextChange(it)) },
            placeholder = stringResource(R.string.hint_text),
            onSendClick = { actionEvent(ModifyRecipeViewModel.ActionEvent.OnBtnSendClick) }
        )
    }
}



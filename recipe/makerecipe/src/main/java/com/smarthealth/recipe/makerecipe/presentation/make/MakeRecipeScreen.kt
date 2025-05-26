package com.smarthealth.recipe.makerecipe.presentation.make

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.smarthealth.recipe.makerecipe.R
import com.smarthealth.recipe.makerecipe.presentation.components.IngredientsList
import com.smarthealth.shared.presentation.components.ShimmerLoadingScreen
import com.smarthealth.shared.presentation.components.TwoColumnGrid

@Composable
fun MakeRecipeScreen(
    state: MakeRecipeScreenState = MakeRecipeScreenState(),
    actionEvent: (MakeRecipeViewModel.ActionEvent) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .windowInsetsPadding(WindowInsets.safeContent),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = state.textSearch,
            onValueChange = { actionEvent(MakeRecipeViewModel.ActionEvent.OnTextChange(it)) },
            placeholder = { Text(stringResource(R.string.hint_make_Text)) },
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = { actionEvent.invoke(MakeRecipeViewModel.ActionEvent.OnMakeClick) },
            modifier = Modifier
                .width(150.dp)
                .height(50.dp)
                .clip(RoundedCornerShape(10.dp)),
            shape = RectangleShape
        ) {
            Text(stringResource(R.string.btnText))
        }
        Spacer(modifier = Modifier.height(16.dp))

        if (state.isMakeBtnClicked) {

            if (state.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    ShimmerLoadingScreen()
                }
            } else {
                if (!state.isSuccess) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(id = R.drawable.img_data_not_found),
                                contentDescription = "No result found",
                                modifier = Modifier.size(120.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("No recipes found", color = Color.Gray)
                        }
                    }
                } else {
                    TwoColumnGrid(
                        items = state.recipeList,
                        onItemClick = { dish ->
                                actionEvent.invoke(MakeRecipeViewModel.ActionEvent.OnItemClick(dish))
                        },
                        imgLoadFail = stringResource(R.string.txtImgLoadFail),
                        modifier = Modifier
                    )
                }

            }

        } else {
            IngredientsList(
                items = state.ingredientsList,
                onCheckedChange = { ingredientName, isChecked ->
                    actionEvent(
                        MakeRecipeViewModel.ActionEvent.OnIngredientCheckedChange(
                            ingredientName,
                            isChecked
                        )
                    )
                }
            )
        }

    }
}

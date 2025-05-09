package com.smarthealth.recipe.searchrecipe.presentation.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.smarthealth.recipe.searchrecipe.R
import com.smarthealth.shared.data.models.GridDish
import com.smarthealth.shared.presentation.components.CustomSearchbar
import com.smarthealth.shared.presentation.components.TwoColumnGrid


@Composable
fun SearchRecipeScreen(
    state: SearchRecipeScreenState = SearchRecipeScreenState(),
    actionEvent: (SearchRecipeViewModel.ActionEvent) -> Unit = {},
    onSearchClick: () -> Unit,
    onItemClick: (GridDish) -> Unit,
    onMakeClick: () -> Unit

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 10.dp)
            .windowInsetsPadding(WindowInsets.safeContent),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomSearchbar(
            value = state.textSearch,
            onValueChange = { actionEvent(SearchRecipeViewModel.ActionEvent.OnTextChange(it)) },
            onButtonClick = onSearchClick,
            buttonText = state.btnSearchText,
            placeholder = state.placeHolderText
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = onMakeClick,
            modifier = Modifier
                .width(150.dp)
                .height(50.dp)
                .clip(RoundedCornerShape(10.dp)),
            shape = RectangleShape
        ) {
            Text(text = state.btnMakeText)
        }
        Spacer(modifier = Modifier.height(16.dp))

        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = Color.LightGray,
                    modifier = Modifier.size(30.dp)
                )
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
                        Text(state.txtNoData, color = Color.Gray)
                    }
                }
            } else {
                TwoColumnGrid(
                    items = state.recipeList,
                    onItemClick = { dish ->
                        onItemClick(dish)
                    }
                )
            }

        }
    }
}

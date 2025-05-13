package com.smarthealth.recipe.makerecipe.presentation.make

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import com.smarthealth.recipe.makerecipe.R
import com.smarthealth.recipe.makerecipe.data.models.IngredientsListItem
import com.smarthealth.shared.presentation.components.TwoColumnGrid
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.smarthealth.shared.data.models.GridDish

@Composable
fun MakeRecipeScreen(
    state: MakeRecipeScreenState = MakeRecipeScreenState(),
    actionEvent: (MakeRecipeViewModel.ActionEvent) -> Unit = {},
    onclick: () -> Unit,
    onItemClick: (GridDish) -> Unit
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
            placeholder = { Text(state.placeHolderText) },
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
            onClick = onclick,
            modifier = Modifier
                .width(150.dp)
                .height(50.dp)
                .clip(RoundedCornerShape(10.dp)),
            shape = RectangleShape
        ) {
            Text("Make Recipe")
        }
        Spacer(modifier = Modifier.height(16.dp))

        if (state.isMakeBtnClicked) {

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
                            Text("No recipes found", color = Color.Gray)
                        }
                    }
                } else {
                    TwoColumnGrid(
                        items = state.recipeList,
                        onItemClick = { dish ->
                            onItemClick(dish)
                        },
                        imgLoadFail = state.txtImgLoadFail
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


@Composable
fun IngredientsList(
    items: List<IngredientsListItem>,
    onCheckedChange: (String, Boolean) -> Unit
) {
    LazyColumn {
        items(items) { item ->
            IngredientsListItem(
                name = item.text,
                image = item.imageRes,
                isChecked = item.isChecked,
                onCheckedChange = { isChecked ->
                    onCheckedChange(item.text, isChecked)
                }
            )
        }
    }
}

@Composable
fun IngredientsListItem(
    name: String,
    image: Int,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(3.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        border = BorderStroke(1.dp, color = Color.LightGray),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .width(60.dp)
                    .height(40.dp)
                    .clip(RectangleShape)
            )

            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f)
            )


            Checkbox(
                checked = isChecked,
                onCheckedChange = onCheckedChange,
            )
        }
    }
}

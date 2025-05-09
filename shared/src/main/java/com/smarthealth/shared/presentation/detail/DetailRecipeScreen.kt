package com.smarthealth.shared.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.smarthealth.shared.presentation.components.CustomTextField
import com.smarthealth.shared.presentation.components.NameText

@Composable
fun RecipeDetailScreen(
    state: DetailRecipeScreenState = DetailRecipeScreenState(),
    onclick: () -> Unit
) {
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .windowInsetsPadding(WindowInsets.safeContent),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DetailScreen(
                title = state.title,
                image = state.imageUrl,
                instructions = state.instructions,
                ingredients = state.ingredients,
                btnModifyText = state.btnModifyText,
                onclick = onclick
            )
        }
    }
}


@Composable
fun DetailScreen(
    title: String,
    image: String,
    instructions: String,
    ingredients: String,
    btnModifyText: String,
    onclick: () -> Unit

) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(image),
            contentDescription = "ImgDish",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop
        )
        NameText(content = title)
        CustomTextField(heading = "Ingredients", text = ingredients, height = 80.0)
        CustomTextField(heading = "Instructions", text = instructions, height = 250.0)
        Button(
            onClick = onclick,
            modifier = Modifier
                .width(150.dp)
                .height(40.dp)
                .clip(RoundedCornerShape(10.dp)),
            shape = RectangleShape
        ) {
            Text(text = btnModifyText)
        }
    }
}



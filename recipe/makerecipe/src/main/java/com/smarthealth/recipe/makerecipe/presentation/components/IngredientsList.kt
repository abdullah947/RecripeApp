package com.smarthealth.recipe.makerecipe.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarthealth.recipe.makerecipe.data.models.IngredientsListItem


@Composable
fun IngredientsList(
    items: List<IngredientsListItem>,
    onCheckedChange: (String, Boolean) -> Unit,
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
    onCheckedChange: (Boolean) -> Unit,
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

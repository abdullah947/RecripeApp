package com.smarthealth.shared.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.smarthealth.shared.data.models.GridDish

@Composable
fun FavouriteList(
    items: List<GridDish>,
    onItemClick: (GridDish) -> Unit,
    imgLoadFail: String
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items) { dish ->
            GridDishItem(
                dish = dish,
                onClick = { onItemClick(dish) },
                txtImgLoadFail = imgLoadFail
            )
        }
    }
}
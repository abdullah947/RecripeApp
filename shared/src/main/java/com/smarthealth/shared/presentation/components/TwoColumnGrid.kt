package com.smarthealth.shared.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.smarthealth.shared.data.models.GridDish

@Composable
fun TwoColumnGrid(
    items: List<GridDish>,
    onItemClick: (GridDish) -> Unit,
    imgLoadFail: String
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
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

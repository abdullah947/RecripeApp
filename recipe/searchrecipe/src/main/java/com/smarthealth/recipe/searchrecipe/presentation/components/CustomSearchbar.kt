package com.smarthealth.recipe.searchrecipe.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.smarthealth.shared.R

@Composable
fun CustomSearchbar(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    onButtonClick: () -> Unit,
    buttonText: String,
    placeholder: String = "",
    suggestions: List<String> = emptyList(),
    onSuggestionClick: (String) -> Unit = {},
) {
    val showSuggestions = value.text.isNotBlank() && suggestions.isNotEmpty()

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clip(RoundedCornerShape(10.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = value,
                onValueChange = onValueChange,
                placeholder = { Text(placeholder) },
                modifier = Modifier
                    .height(56.dp)
                    .weight(1f),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            Button(
                onClick = onButtonClick,
                modifier = Modifier
                    .width(100.dp)
                    .height(56.dp),
                shape = RectangleShape
            ) {
                Text(buttonText)
            }
        }

        if (showSuggestions) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 200.dp)
                    .padding(horizontal = 8.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(color = colorResource(R.color.light_gray_custom))
            ) {
                items(suggestions) { suggestion ->
                    Text(
                        text = suggestion,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                            .clickable {
                                onSuggestionClick(suggestion)
                            }
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun CustomSearchbarPreview() {
    CustomSearchbar(
        value = TextFieldValue(""),
        onValueChange = {},
        onButtonClick = {},
        buttonText = "Search",
        placeholder = "Recipe"
    )
}

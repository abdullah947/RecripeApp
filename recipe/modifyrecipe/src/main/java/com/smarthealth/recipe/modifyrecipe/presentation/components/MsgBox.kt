package com.smarthealth.recipe.modifyrecipe.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.smarthealth.recipe.modifyrecipe.R

@Composable
fun MsgBox(text: String, role: String) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = if (role == "user") Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .widthIn(max = 300.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 48f,
                        topEnd = 48f,
                        bottomStart = if (role == "model") 0f else 48f,
                        bottomEnd = if (role == "user") 0f else 48f
                    )
                )
                .background(if (role == "user") Color.DarkGray else colorResource(R.color.pink_custom))
        ) {
            Text(
                text = text, color = Color.White,
                modifier = Modifier
                    .padding(10.dp)
            )

        }
    }
}

@Preview
@Composable
fun MsgSentBoxPreview() {
    MsgBox(text = "Hello", "user")
}

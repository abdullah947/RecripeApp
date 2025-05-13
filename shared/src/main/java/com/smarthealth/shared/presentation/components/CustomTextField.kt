package com.smarthealth.shared.presentation.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarthealth.shared.R

@Composable
fun CustomTextField(
    heading: String,
    text: String,
    height: Double,

) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .background(
                color = colorResource(R.color.light_gray_custom),
                shape = RoundedCornerShape(12.dp)
            )
            .border(2.dp, Color.LightGray, shape = RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        val clipboardManager = LocalClipboardManager.current
        val context = LocalContext.current
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "Info",
            tint = Color.Gray,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .clickable {  clipboardManager.setText(AnnotatedString(text))
                    Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show() }
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = heading,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .fillMaxWidth()
            )
            val scrollState = rememberScrollState()
            Text(
                text = text,
                color = Color.Black,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height.dp)
                    .verticalScroll(scrollState)
            )
        }
    }
}

@Composable
fun NameText(
    content: String,
) {
    Box(
        modifier = Modifier
            .padding(5.dp)
            .width(250.dp)
            .height(45.dp)
            .border(2.dp, Color.LightGray, shape = RoundedCornerShape(12.dp))
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = content,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier
                .basicMarquee(
                    iterations = Int.MAX_VALUE,
                    repeatDelayMillis = 500,
                    velocity = 30.dp
                )
        )
    }
}

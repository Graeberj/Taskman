package com.graeberj.taskman.auth.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = {

        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = Color.White
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            fontSize = 16.sp
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CustomButtonPreviewLoader() {
    CustomButton(text = "LOG IN", onClick = {})
}
package com.graeberj.taskman.core.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.graeberj.taskman.R
import com.graeberj.taskman.ui.theme.Black
import com.graeberj.taskman.ui.theme.White

@Composable
fun TopGreeting(
    @StringRes greetingResId: Int? = null,
    header: (@Composable BoxScope.() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit
) {
    val headerWeight = if (header == null) {
        1.5f
    } else {
        0.8f
    }
    val maxWeight = 10
    val contentWeight = maxWeight - headerWeight
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Black
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            if (greetingResId != null) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(headerWeight),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(greetingResId),
                        style = TextStyle(
                            fontSize = 28.sp,
                            fontFamily = FontFamily(Font(R.font.inter)),
                            fontWeight = FontWeight.Bold,
                            color = White,
                        )
                    )
                }
            } else if (header != null) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(headerWeight)
                ) {
                    header()
                }

            }
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(contentWeight)
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            ) {
                Box(modifier = Modifier.padding(16.dp)) {
                    content()
                }
            }
        }
    }
}
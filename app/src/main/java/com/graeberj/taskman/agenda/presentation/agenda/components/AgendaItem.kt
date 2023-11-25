package com.graeberj.taskman.agenda.presentation.agenda.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.graeberj.taskman.agenda.data.model.AgendaItem
import com.graeberj.taskman.ui.theme.Black
import com.graeberj.taskman.ui.theme.White
import java.time.format.DateTimeFormatter

@Composable
fun AgendaItem(
    item: AgendaItem,
    color: Color,
    onOptionsClick: () -> Unit,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val itemDate by remember {
        derivedStateOf {
            val dateFormatter = DateTimeFormatter.ofPattern("dd MMM, HH:mm")
            item.time.format(dateFormatter)
        }
    }
    val textColor = if (item is AgendaItem.Task) White else Black
    val descriptionColor = if (item is AgendaItem.Task) White else DarkGray

    Column(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 130.dp)
            .background(color = color, shape = RoundedCornerShape(20.dp))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.then(
                    if (item is AgendaItem.Task) {
                        Modifier.clickable {
                            onItemClick()
                        }
                    } else Modifier
                ).weight(9f)
            ) {
                Icon(
                    imageVector = if (item is AgendaItem.Task && item.isDone) Icons.Outlined.CheckCircleOutline else Icons.Outlined.Circle,
                    contentDescription = "title",
                    tint = textColor
                )
                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = item.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = textColor,
                    textDecoration = if (item is AgendaItem.Task && item.isDone) TextDecoration.LineThrough else null
                )
            }
            IconButton(onClick = onOptionsClick, modifier = Modifier.weight(1f)) {
                Icon(
                    imageVector = Icons.Default.MoreHoriz,
                    contentDescription = "options",
                    tint = textColor
                )
            }
        }
        Text(
            text = item.description,
            fontSize = 14.sp,
            color = descriptionColor,
            modifier = Modifier.padding(start = 36.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = itemDate,
            fontSize = 14.sp,
            color = descriptionColor,
            modifier = Modifier.align(
                Alignment.End
            )
        )
    }
}
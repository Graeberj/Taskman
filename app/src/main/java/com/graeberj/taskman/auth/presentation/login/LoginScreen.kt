package com.graeberj.taskman.auth.presentation.login


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.graeberj.taskman.R
import com.graeberj.taskman.auth.presentation.components.CustomButton
import com.graeberj.taskman.auth.presentation.components.CustomTextField
import com.graeberj.taskman.ui.theme.Black

@Composable
fun LoginScreen() {
    Column(modifier = Modifier.fillMaxSize().background(Black)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.welcome_back),
                    style = TextStyle(
                        fontSize = 28.sp,
                        lineHeight = 30.sp,
                        fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFFFFFF),
                    )
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(8f)
                    .background(
                        color = Color(0xFFFFFFFF),
                        shape = RoundedCornerShape(size = 30.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomTextField(
                    value = "Email Address",
                    onValueChange = {},
                    modifier = Modifier.padding(top = 40.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                CustomTextField(value = "Password", onValueChange = {})
                Spacer(modifier = Modifier.height(25.dp))
                CustomButton(
                    text = "Log In",
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(100.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(onClick = {}) {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.Gray,
                                        fontSize = 14.sp
                                    )
                                ) {
                                    append("DON'T HAVE AN ACCOUNT?")
                                    append(" ")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 14.sp
                                    )
                                ) {
                                    append("SIGN UP")
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}


@Composable
@Preview
fun PreviewLoginScreen() {
    LoginScreen()
}
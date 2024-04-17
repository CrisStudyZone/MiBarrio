package com.example.c17_129_kotlin.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.c17_129_kotlin.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun Log() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color(android.graphics.Color.parseColor("#ffffff"))),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier.padding(30.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(end = 8.dp)
                    .weight(1f)
                    .height(55.dp),
                border = BorderStroke(
                    1.dp,
                    color = Color(android.graphics.Color.parseColor("#28A9E2"))
                ),
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Login with ",
                        color = Color(android.graphics.Color.parseColor("#EA7B29"))
                    )
                    Image(
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = "google",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f)
                    .height(55.dp),
                border = BorderStroke(
                    1.dp,
                    color = Color(android.graphics.Color.parseColor("#28A9E2"))
                ),
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Login with ",
                        color = Color(android.graphics.Color.parseColor("#EA7B29"))
                    )
                    Image(
                        painter = painterResource(id = R.drawable.facebook),
                        contentDescription = "facebook",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
            verticalAlignment = Alignment.CenterVertically)
        {
            Box(modifier = Modifier
                .height(1.dp)
                .weight(1f)
                .background(color = Color(android.graphics.Color.parseColor("#2CAADE")))
            )
            Text(text = "OR",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp),
                color = Color(android.graphics.Color.parseColor("#2CAADE"))
            )
            Box(modifier = Modifier
                .height(1.dp)
                .weight(1f)
                .background(color = Color(android.graphics.Color.parseColor("#2caade")))
            )

        }

        var email by remember { mutableStateOf("") }

        var password by remember { mutableStateOf("") }

        var passwordVisible by rememberSaveable { mutableStateOf(false)

        }

        TextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(66.dp)
                .padding(start = 50.dp, end = 50.dp, top = 8.dp, bottom = 8.dp)
                .border(
                    1.dp, Color(android.graphics.Color.parseColor("#18ABA3"))
                )
                .background(
                    Color(android.graphics.Color.parseColor("#18ABA3")),
                    RoundedCornerShape(10.dp)
                ),
            label = { Text(text = "Email ID / Phone No.") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                Color.White,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
            )
        )

        TextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(66.dp)
                .padding(start = 50.dp, end = 50.dp, top = 8.dp, bottom = 8.dp)
                .border(
                    1.dp, Color(android.graphics.Color.parseColor("#18ABA3"))
                )
                .background(
                    Color(android.graphics.Color.parseColor("#18ABA3")),
                    RoundedCornerShape(10.dp)
                ),
            label = { Text("Password") },
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                Color.White,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
            ),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(start = 50.dp, end = 50.dp, top = 8.dp, bottom = 8.dp)
                    .height(66.dp)
                    .fillMaxWidth()
                    .background(
                        color = Color(android.graphics.Color.parseColor("#2CAADE")),
                        shape = RoundedCornerShape(40.dp)
                    ),
                border = BorderStroke(
                    1.dp,
                    color = Color(android.graphics.Color.parseColor("#28A9E2"))
                ),
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                shape = RoundedCornerShape(40.dp)
            ) {
                Text(text = "LOGIN")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Forgot Password ?",
                textAlign = TextAlign.Center,
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.width(15.dp))

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp, end = 8.dp)
                    .height(30.dp)
                    .widthIn(min = 100.dp),
                border = BorderStroke(
                    1.dp,
                    color = Color(android.graphics.Color.parseColor("#28A9E2"))
                ),
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Reset Password",
                    color = Color(android.graphics.Color.parseColor("#EA7B29"))
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(start = 64.dp, end = 64.dp, top = 8.dp, bottom = 8.dp)
                .height(30.dp)
                .widthIn(min = 300.dp),
            border = BorderStroke(
                1.dp,
                color = Color(android.graphics.Color.parseColor("#28A9E2"))
            ),
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Sign up..",
                color = Color(android.graphics.Color.parseColor("#EA7B29"))
            )
        }

    }
}
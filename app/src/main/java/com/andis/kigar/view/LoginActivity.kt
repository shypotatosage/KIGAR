package com.andis.kigar.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.andis.kigar.R
import com.andis.kigar.navigation.Screen
import com.andis.kigar.ui.theme.*
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import java.util.*

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KIGARTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}

@Composable
fun LoginScreen(navController: NavHostController) {
    var username = remember {
        mutableStateOf("")
    }

    var usernameError = remember {
        mutableStateOf(false)
    }

    var password = remember {
        mutableStateOf("")
    }

    var passwordError = remember {
        mutableStateOf(false)
    }

    var passwordVisible = remember {
        mutableStateOf(false)
    }

    var loginSuccess = remember {
        mutableStateOf(false)
    }

    var loading = remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    val image = if (passwordVisible.value) {
        Icons.Filled.Visibility
    } else {
        Icons.Filled.VisibilityOff
    }

    ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsWithImePadding()
                .verticalScroll(rememberScrollState())
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (loading.value) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                    CircularProgressIndicator(
                        color = SoftBlack
                    )
                }
            } else {
                Image(
                    painter = painterResource(id = R.drawable.kubota_logo_svg),
                    contentDescription = "Andis Logo",
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 0.dp, 24.dp)
                        .fillMaxWidth(0.5f)
                )
                Text(
                    text = "Silahkan Masuk",
                    fontSize = 28.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 0.dp, 0.dp)
                )
                Column(
                    modifier = Modifier
                        .padding(24.dp, 24.dp)
                ) {
                    Surface(
                        shadowElevation = 4.dp,
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        BasicTextField(
                            value = username.value,
                            onValueChange = {
                                username.value = it.trim().uppercase(Locale.getDefault())
                            },
                            enabled = true,
                            singleLine = true,
                            maxLines = 1,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    if (username.value == "") {
                                        usernameError.value = true
                                    } else {
                                        usernameError.value = false
                                    }
                                }
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    BorderStroke(
                                        width = 0.4.dp,
                                        color = Gray300
                                    ),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .navigationBarsWithImePadding(),
                            decorationBox = { innerTextField ->
                                TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent,
                                    textColor = SoftBlack
                                )
                                Box(
                                    modifier = Modifier
                                        .padding(16.dp, 10.dp)
                                ) {
                                    if (username.value.isEmpty()) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(0.dp, 0.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Box() {
                                                innerTextField()
                                                Text(
                                                    text = "Username",
                                                    color = Gray300,
                                                    fontSize = 16.sp,
                                                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                                                )
                                            }
                                        }
                                    } else {
                                        Row(
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            modifier = Modifier.fillMaxSize(),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            innerTextField()
                                        }
                                    }
                                }
                            },
                            textStyle = TextStyle(
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                fontSize = 16.sp
                            )
                        )
                    }
                    if (usernameError.value) {
                        Text(
                            text =
                            if (username.value.isNotEmpty()) {
                                "Username tidak terdaftar"
                            } else {
                                "Wajib memasukkan Username"
                            },
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontSize = 13.sp,
                            color = Red500,
                            modifier = Modifier
                                .padding(6.dp, 4.dp, 6.dp, 0.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Surface(
                        shadowElevation = 4.dp,
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        BasicTextField(
                            value = password.value,
                            onValueChange = {
                                password.value = it.trim()
                            },
                            visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                            enabled = true,
                            singleLine = true,
                            maxLines = 1,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    if (password.value == "") {
                                        passwordError.value = true
                                    } else {
                                        passwordError.value = false
                                    }
                                }
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    BorderStroke(
                                        width = 0.4.dp,
                                        color = Gray300
                                    ),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .navigationBarsWithImePadding(),
                            decorationBox = { innerTextField ->
                                TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent,
                                    textColor = SoftBlack
                                )
                                Box(
                                    modifier = Modifier
                                        .padding(16.dp, 0.dp)
                                ) {
                                    if (password.value.isEmpty()) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(0.dp, 0.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Box() {
                                                innerTextField()
                                                Text(
                                                    text = "Password",
                                                    color = Gray300,
                                                    fontSize = 16.sp,
                                                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                                                )
                                            }
                                            IconButton(onClick = {
                                                passwordVisible.value = !passwordVisible.value
                                            }) {
                                                Icon(imageVector = image, "")
                                            }
                                        }
                                    } else {
                                        Row(
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            modifier = Modifier.fillMaxSize(),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            innerTextField()
                                            IconButton(onClick = {
                                                passwordVisible.value = !passwordVisible.value
                                            }) {
                                                Icon(imageVector = image, "")
                                            }
                                        }
                                    }
                                }
                            },
                            textStyle = TextStyle(
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                fontSize = 16.sp
                            )
                        )
                        if (passwordError.value) {
                            Text(
                                text = "Wajib memasukkan Password",
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                fontSize = 13.sp,
                                color = Red500,
                                modifier = Modifier
                                    .padding(6.dp, 4.dp, 6.dp, 0.dp)
                            )
                        }
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 12.dp, 0.dp, 0.dp)
                ) {
                    Button(
                        onClick = {
                            loading.value = true

                            if (username.value == "") {
                                usernameError.value = true
                            } else {
                                usernameError.value = false
                            }

                            if (password.value == "") {
                                passwordError.value = true
                            } else {
                                passwordError.value = false
                            }
                        },
                        modifier = Modifier
                            .padding(8.dp, 0.dp),
                        shape = RoundedCornerShape(15.dp),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = KubotaGreen)
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(24.dp, 4.dp),
                            text = "Masuk",
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                            color = GreyBackground
                        )
                    }
                }
            }
        }
    }

    if (loginSuccess.value) {
        LaunchedEffect(Unit) {
            if (loginSuccess.value) {
                loginSuccess.value = false

                navController.navigate(Screen.ListData.route) {
                    popUpTo(Screen.Login.route) { inclusive = true }
                }
            }
        }
    }
}
package com.andis.kigar.view

import android.Manifest
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import com.andis.kigar.R
import com.andis.kigar.helper.RequestMultiplePermissions
import com.andis.kigar.navigation.Screen
import com.andis.kigar.ui.theme.GreyBackground
import com.andis.kigar.ui.theme.KIGARTheme
import com.andis.kigar.ui.theme.SoftBlack
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.delay

class SplashScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KIGARTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SplashScreen(1f)
                }
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AnimatedSplashScreen(navController: NavHostController) {
    RequestMultiplePermissions(
        permissions = listOf(
            Manifest.permission.INTERNET
        )
    )

    var startAnimation by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    val alphaAnimation = animateFloatAsState(
        targetValue = if (startAnimation) { 1f } else { 0f },
        animationSpec = tween(
            durationMillis = 2000
        )
    )

    LaunchedEffect(key1 = true) {
        val preferences = context.getSharedPreferences("vendor", Context.MODE_PRIVATE)

        startAnimation = true
        delay(3000)

        if (preferences.getString("vendor_id", "") != "") {
        } else {
            navController.navigate(Screen.AddData.route)
        }
    }

    SplashScreen(alphaAnimation.value)
}

@Composable
fun SplashScreen(alpha: Float) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .alpha(alpha)
            .padding(0.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.kubota_logo_svg),
            contentDescription = "Kubota Logo",
            modifier = Modifier
                .width(275.dp)
        )
        Text(
            modifier = Modifier
                .padding(8.dp, 8.dp),
            text = "Data Kartu Garansi",
            fontSize = 22.sp,
            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
            color = SoftBlack
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .alpha(alpha),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(20.dp, 16.dp),
            text = "Developed By\nMicheila Jiemesha",
            fontSize = 19.sp,
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            color = SoftBlack,
            textAlign = TextAlign.Center
        )
    }
}
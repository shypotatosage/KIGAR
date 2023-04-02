package com.andis.kigar.view

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.andis.kigar.R
import com.andis.kigar.navigation.Screen
import com.andis.kigar.ui.theme.*

class AddDataActivity : ComponentActivity() {
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
fun AddDataScreen(navController: NavHostController) {
    var imageUri1 by remember {
        mutableStateOf<Uri?>(null)
    }

    val context = LocalContext.current

    val bitmap1 = remember {
        mutableStateOf<Bitmap?>(null)
    }

    val launcher1 = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUri1 = uri
    }

    imageUri1?.let {
        if (Build.VERSION.SDK_INT < 28) {
            bitmap1.value = MediaStore.Images
                .Media.getBitmap(context.contentResolver, it)
        } else {
            val source = ImageDecoder.createSource(context.contentResolver, it)
            bitmap1.value = ImageDecoder.decodeBitmap(source)
        }
    }

    var imageUri2 by remember {
        mutableStateOf<Uri?>(null)
    }

    val bitmap2 = remember {
        mutableStateOf<Bitmap?>(null)
    }

    val launcher2 = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUri2 = uri
    }

    imageUri2?.let {
        if (Build.VERSION.SDK_INT < 28) {
            bitmap2.value = MediaStore.Images
                .Media.getBitmap(context.contentResolver, it)
        } else {
            val source = ImageDecoder.createSource(context.contentResolver, it)
            bitmap2.value = ImageDecoder.decodeBitmap(source)
        }
    }

    var imageUri3 by remember {
        mutableStateOf<Uri?>(null)
    }

    val bitmap3 = remember {
        mutableStateOf<Bitmap?>(null)
    }

    val launcher3 = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUri3 = uri
    }

    imageUri3?.let {
        if (Build.VERSION.SDK_INT < 28) {
            bitmap3.value = MediaStore.Images
                .Media.getBitmap(context.contentResolver, it)
        } else {
            val source = ImageDecoder.createSource(context.contentResolver, it)
            bitmap3.value = ImageDecoder.decodeBitmap(source)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Image(
            painter = painterResource(id = R.drawable.kubota_logo_svg),
            contentDescription = "Kubota Logo",
            modifier = Modifier
                .width(225.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier
                .padding(8.dp, 12.dp, 8.dp, 0.dp),
            text = "Foto KTP",
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            color = SoftBlack
        )
        if (bitmap1.value == null) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(20.dp, 8.dp)
                    .background(color = GreyBackground)
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .height(150.dp)
                        .background(color = GreyBackground)
                        .align(alignment = Alignment.CenterVertically),
                    shape = RoundedCornerShape(16.dp),
                    color = Color.White,
                    shadowElevation = 5.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Grey200)
                            .clickable {
                                launcher1.launch("image/*")
                            },
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_image_24),
                            contentDescription = "Add Image",
                            modifier = Modifier
                                .size(80.dp)
                        )
                    }
                }
            }
        } else {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(20.dp, 8.dp)
                    .background(color = GreyBackground)
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .wrapContentHeight()
                        .background(color = GreyBackground)
                        .align(alignment = Alignment.CenterVertically),
                    shape = RoundedCornerShape(16.dp),
                    color = Color.White,
                    shadowElevation = 5.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .background(Grey200)
                            .clickable {
                                launcher1.launch("image/*")
                            },
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            bitmap = bitmap1.value!!.asImageBitmap(),
                            contentDescription = "Add Image",
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            modifier = Modifier
                .padding(8.dp, 12.dp, 8.dp, 0.dp),
            text = "Foto Nomor Mesin",
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            color = SoftBlack
        )
        if (bitmap2.value == null) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(20.dp, 8.dp)
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .height(150.dp)
                        .align(alignment = Alignment.CenterVertically),
                    shape = RoundedCornerShape(16.dp),
                    color = Color.White,
                    shadowElevation = 5.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Grey200)
                            .clickable {
                                launcher2.launch("image/*")
                            },
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_image_24),
                            contentDescription = "Add Image",
                            modifier = Modifier
                                .size(80.dp)
                        )
                    }
                }
            }
        } else {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(20.dp, 8.dp)
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .wrapContentHeight()
                        .align(alignment = Alignment.CenterVertically),
                    shape = RoundedCornerShape(16.dp),
                    color = Color.White,
                    shadowElevation = 5.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .background(Grey200)
                            .clickable {
                                launcher2.launch("image/*")
                            },
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            bitmap = bitmap2.value!!.asImageBitmap(),
                            contentDescription = "Add Image",
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            modifier = Modifier
                .padding(8.dp, 12.dp, 8.dp, 0.dp),
            text = "Foto Kartu Garansi",
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            color = SoftBlack
        )
        if (bitmap3.value == null) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(20.dp, 8.dp)
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .height(150.dp)
                        .align(alignment = Alignment.CenterVertically),
                    shape = RoundedCornerShape(16.dp),
                    color = Color.White,
                    shadowElevation = 5.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Grey200)
                            .clickable {
                                launcher3.launch("image/*")
                            },
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_image_24),
                            contentDescription = "Add Image",
                            modifier = Modifier
                                .size(80.dp)
                        )
                    }
                }
            }
        } else {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(20.dp, 8.dp)
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .wrapContentHeight()
                        .align(alignment = Alignment.CenterVertically),
                    shape = RoundedCornerShape(16.dp),
                    color = Color.White,
                    shadowElevation = 5.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .background(Grey200)
                            .clickable {
                                launcher3.launch("image/*")
                            },
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            bitmap = bitmap3.value!!.asImageBitmap(),
                            contentDescription = "Add Image",
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        FloatingActionButton(
            onClick = {
                navController.navigate(Screen.ListData.route)
            },
            containerColor = KubotaGreen,
            shape = RoundedCornerShape(100.dp),
            modifier = Modifier
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.List,
                contentDescription = "List Page",
                tint = Color.White
            )
        }
    }
}
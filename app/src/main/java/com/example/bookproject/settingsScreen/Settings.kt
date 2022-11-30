@file:JvmName("SettingsKt")

package com.example.bookproject.settingsScreen

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.bookproject.MainActivity
import com.example.bookproject.R
import com.example.bookproject.ui.theme.Shapes
import com.google.accompanist.permissions.*


@ExperimentalMaterialApi
@Composable
fun Settings() {
    val context = LocalContext.current
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("theme", MODE_PRIVATE)

    var colorBack = colorResource(id = R.color.lightColorBack)
    var colorText = colorResource(id = R.color.darkColorText)
    if (!sharedPreferences.getBoolean("THEME_KEY", false)) { //false is the default value
        colorBack = colorResource(id = R.color.darkColorBack)
        colorText = colorResource(id = R.color.lightColorText)
    }


    Column(
        modifier = Modifier
            .background(colorBack)
            .fillMaxHeight()
    ) {
        HeaderText()
        GeneralOptionsUI(colorText)
        SupportOptionsUI(colorBack, colorText)
    }
}


@Composable
fun HeaderText() {
    Text(
        text = stringResource(id = R.string.settings_tab_title),
        fontFamily = FontFamily.Serif,
        color = colorResource(id = R.color.colorSecondary),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp, bottom = 10.dp),
        fontWeight = FontWeight.ExtraBold,
        fontSize = 16.sp
    )
}


@ExperimentalMaterialApi
@Composable
fun GeneralOptionsUI(colorBack: Color) {
    val context = LocalContext.current

    var sharedPreferences: SharedPreferences = context.getSharedPreferences("theme", MODE_PRIVATE)
    var editor: SharedPreferences.Editor = sharedPreferences.edit()


    Column(
        modifier = Modifier
            .padding(horizontal = 14.dp)
            .padding(top = 10.dp)
    ) {
        Text(
            text = stringResource(R.string.general_settings),
            fontFamily = FontFamily.Serif,
            color = colorResource(id = R.color.colorSecondary),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 8.dp)
        )
        GeneralSettingItem(
            icon = R.drawable.ic_rounded_notification,
            mainText = stringResource(R.string.Theme),
            subText = stringResource(R.string.customize_theme_text),
            onClick = {
                Log.d("@@@", "Worked!")
                if (sharedPreferences.getBoolean("THEME_KEY", false)) {
                    editor.putBoolean("THEME_KEY", false);
                    editor.apply()
                    Log.d("@@@", "Edited false!")
                } else {
                    editor.putBoolean("THEME_KEY", true)
                    editor.apply()
                    Log.d("@@@", "Edited true!")
                }
                context.startActivity(Intent(context, MainActivity::class.java))
                (context as MainActivity).finishAffinity()
            }, backColor = colorBack
        )
        GeneralSettingItem(
            icon = R.drawable.ic_more,
            mainText = stringResource(R.string.more_customization_text),
            subText = stringResource(R.string.customization_text_description),
            onClick = {}, colorBack
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun GeneralSettingItem(
    icon: Int,
    mainText: String,
    subText: String,
    onClick: () -> Unit,
    backColor: Color
) {
    Card(
        onClick = { onClick() },
        backgroundColor = backColor,
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(),
        elevation = 0.dp,
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(shape = Shapes.medium)
                        .background(colorResource(id = R.color.colorPrimary))
                ) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = "",
                        tint = Color.Unspecified,
                        modifier = Modifier.padding(8.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))
                Column(
                    modifier = Modifier.offset(y = (2).dp)
                ) {
                    Text(
                        text = mainText,
                        fontFamily = FontFamily.Serif,
                        color = colorResource(id = R.color.colorSecondary),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                    )

                    Text(
                        text = subText,
                        fontFamily = FontFamily.Serif,
                        color = Color.Gray,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.offset(y = (-4).dp)
                    )
                }
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_right_arrow),
                contentDescription = "",
                modifier = Modifier.size(16.dp)
            )

        }
    }
}

@ExperimentalMaterialApi
@Composable
fun SupportOptionsUI(colorBack: Color, colorText: Color) {
    Column(
        modifier = Modifier
            .padding(horizontal = 14.dp)
            .padding(top = 10.dp)
            .background(colorBack)
    ) {
        Text(
            text = stringResource(R.string.support_text),
            fontFamily = FontFamily.Serif,
            color = colorText,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 8.dp)
        )
        SupportItem(
            icon = R.drawable.ic_whatsapp,
            mainText = stringResource(R.string.contact_text),
            onClick = {},
            color = colorText
        )
        SupportItem(
            icon = R.drawable.ic_feedback,
            mainText = stringResource(R.string.feedback_text),
            onClick = {},
            color = colorText
        )
        SupportItem(
            icon = R.drawable.ic_privacy_policy,
            mainText = stringResource(R.string.privacy_policy_text),
            onClick = {},
            color = colorText
        )
        SupportItem(
            icon = R.drawable.ic_about,
            mainText = stringResource(R.string.about_text),
            onClick = {},
            color = colorText
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun SupportItem(icon: Int, mainText: String, onClick: () -> Unit, color: Color) {
    Card(
        onClick = { onClick() },
        backgroundColor = color,
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(),
        elevation = 0.dp,
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(shape = Shapes.medium)
                        .background(colorResource(id = R.color.colorPrimary))
                ) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = "",
                        tint = Color.Unspecified,
                        modifier = Modifier.padding(8.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Text(
                    text = mainText,
                    fontFamily = FontFamily.Serif,
                    color = colorResource(id = R.color.colorSecondary),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_right_arrow),
                contentDescription = "",
                modifier = Modifier.size(16.dp)
            )

        }
    }
}


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SinglePermission() {
    val permissionState =
        rememberPermissionState(permission = Manifest.permission.ACCESS_FINE_LOCATION)
    val lifecycleOwner = LocalLifecycleOwner.current

    Log.d("@@", "asking for permission")

    DisposableEffect(key1 = lifecycleOwner, effect = {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> {
                    permissionState.launchPermissionRequest()
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    })


    when {
        permissionState.hasPermission -> {
            Text(text = "Reading external permission is granted")
        }
        permissionState.shouldShowRationale -> {
            Column {
                Text(text = "Reading external permission is required by this app")
            }
        }
        !permissionState.hasPermission && !permissionState.shouldShowRationale -> {
            Text(text = "Permission fully denied. Go to settings to enable")
        }
    }
}






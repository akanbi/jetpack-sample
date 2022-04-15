package com.akanbi.jetpacksample

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.akanbi.hilt.presentation.UserActivity
import com.akanbi.jetpacksample.ui.theme.JetpacksampleTheme
import kotlin.reflect.KClass

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpacksampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavigateButton(
                        context = this,
                        title = "Hilt",
                        clazzToNavigate = UserActivity::class)
                }
            }
        }
    }
}

@Composable
fun NavigateButton(context: Context? = null, title: String, clazzToNavigate: KClass<*>) {
    Button(
        onClick = {
            val intent =
                context?.packageManager?.getLaunchIntentForPackage("com.akanbi.hilt.presentation.UserActivity")
            context?.startActivity(intent)
        }) {
        Text(text = title)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpacksampleTheme {
        NavigateButton(
            title = "Hilt",
            clazzToNavigate = UserActivity::class)
    }
}
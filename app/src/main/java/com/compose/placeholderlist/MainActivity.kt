package com.compose.placeholderlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.compose.placeholderlist.ui.initPlaceHolderList
import com.compose.placeholderlist.ui.theme.PlaceHolderListTheme
import com.compose.placeholderlist.viewmodel.PlaceHolderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlaceHolderListTheme { // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    initPlaceHolderList()
                }
            }
        }
    }
}

@Composable fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true) @Composable fun DefaultPreview() {
    PlaceHolderListTheme {
        Greeting("Android")
    }
}
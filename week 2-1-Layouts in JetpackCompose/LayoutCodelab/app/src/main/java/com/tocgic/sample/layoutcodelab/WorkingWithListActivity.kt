package com.tocgic.sample.layoutcodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.tocgic.sample.layoutcodelab.ui.theme.LayoutCodelabTheme

class WorkingWithListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutCodelabTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    SimpleList()
                }
            }
        }
    }
}

@Composable
fun SimpleList() {
    Column {
        repeat(100) {
            Text(text = "Item #$it")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SimpleListPreview() {
    LayoutCodelabTheme {
        SimpleList()
    }
}
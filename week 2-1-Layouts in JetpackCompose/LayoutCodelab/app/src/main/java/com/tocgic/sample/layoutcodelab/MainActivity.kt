package com.tocgic.sample.layoutcodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.LaptopChromebook
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tocgic.sample.layoutcodelab.ui.theme.LayoutCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutCodelabTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(color = MaterialTheme.colors.background) {
//                    PhotographerCard()
//                }
                LayoutCodelab()
            }
        }
    }
}

@Composable
fun PhotographerCard(modifier: Modifier = Modifier) {
    Row(
        modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.surface)
            .clickable(onClick = {})
            .padding(16.dp)
    ) {
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {

        }
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(text = "Alfred Sisley", fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("3 minutes ago", style = MaterialTheme.typography.body2)
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PhotographerCardPreview() {
//    LayoutCodelabTheme {
//        PhotographerCard()
//    }
//}

//@Preview(
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//    showBackground = true
//)
//@Composable
//fun PhotographerCardPreviewDark() {
//    LayoutCodelabTheme {
//        PhotographerCard()
//    }
//}

/*
4. Slot API

@Composable
fun Button(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    ...
    content: @Composable () -> Unit
)

후행 람다구문(content: @Composable () -> Unit) 을 통해 확장할 수 있다

Button {
    Row {
        MyImage()
        Spacer(4.dp)
        Text("Button")
    }
}
 */

@Composable
fun LayoutCodelab() {
    // content: @Composable (PaddingValues) -> Unit
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "LayoutCodelab")
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.LaptopChromebook, contentDescription = null)
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.HealthAndSafety, contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->
        // 2.Apply the modifier when calling the composable that will add the extra padding just when needed:
        BodyContent(Modifier.padding(paddingValues).padding(8.dp))
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    // 1.Apply the modifier to the only direct child inside the composable so that all calls to BodyContent apply the extra padding:
    // Column(modifier = modifier.padding(8.dp) {
    MyOwnColumn(modifier = modifier.padding(8.dp)) {
        Text("MyOwnColumn")
        Text("places items")
        Text("vertically.")
        Text("We've done it by hand!")
    }
}

@Preview
@Composable
fun LayoutCodelabPreview() {
    LayoutCodelabTheme {
        LayoutCodelab()
    }
}
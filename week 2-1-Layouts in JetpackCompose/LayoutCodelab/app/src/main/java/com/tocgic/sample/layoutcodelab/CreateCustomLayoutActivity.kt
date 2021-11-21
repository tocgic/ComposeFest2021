package com.tocgic.sample.layoutcodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tocgic.sample.layoutcodelab.ui.theme.LayoutCodelabTheme

class CreateCustomLayoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutCodelabTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

fun Modifier.firstBaselineToTop(
    firstBaselineToTop: Dp
) = this.then(
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints) // 컴포저블을 측정

        // 컴포저블에 첫 번째 기준선이 있는지 확인
        check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
        val firstBaseline = placeable[FirstBaseline]

        // 패딩이 있는 컴포저블 높이 - 첫 번째 기준선
        val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
        val height = placeable.height + placeableY
        layout(placeable.width, height) {
            // 컴포저블이 배치되는 위치
            placeable.placeRelative(0, placeableY)
        }
    }
)

@Preview
@Composable
fun TextWithPaddingToBaselinePreview() {
    LayoutCodelabTheme {
        Text("Hi there!", Modifier.firstBaselineToTop(32.dp).fillMaxWidth())
    }
}

@Preview
@Composable
fun TextWithNormalPaddingPreview() {
    LayoutCodelabTheme {
        Text("Hi there!", Modifier.padding(top = 32.dp).fillMaxWidth())
    }
}

@Composable
fun MyOwnColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        // child 를 측정할 때 나중에 화면에 어린이를 올바르게 배치할 수 있도록 각 행의 너비와 최대 높이도 추적해야 합니다.

        // child 보기를 더 이상 제한하지 말고 주어진 제약 조건으로 측정
        // 측정된 child 목록
        val placeables = measurables.map { measurable ->
            // 각 child 측정
            measurable.measure(constraints)
        }

        // child 배치한 y 좌표를 추적
        var yPosition = 0

        // 레이아웃의 크기를 최대한 크게 설정
        layout(constraints.maxWidth, constraints.maxHeight) {
            // 부모 레이아웃에 자식 배치
            placeables.forEach { placeable ->
                placeable.placeRelative(x = 0, y = yPosition)

                // 배치된 y 좌표를 기록
                yPosition += placeable.height
            }
        }
    }
}
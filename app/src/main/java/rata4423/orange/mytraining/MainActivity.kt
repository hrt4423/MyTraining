package rata4423.orange.mytraining

import android.os.Bundle
//import android.util.Size
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
//import androidx.annotation.Size
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import rata4423.orange.mytraining.ui.theme.MyTrainingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTrainingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun Meter() {
    Canvas(
        modifier = Modifier
            .size(200.dp)
            .padding(8.dp),
        onDraw = {
            drawArc(
                color = Color.Black,
                //描画開始位置を指定（0：右, 90：下, 180：左, 270：上）
                startAngle = 270f,
                //実際に描画する角度
                sweepAngle = 180f,
                useCenter = false,
                size = Size(size.width, size.height),
                style = Stroke(width = 20f)
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyTrainingTheme {
        Column{
            Greeting("Android")
            Meter()
        }
    }
}
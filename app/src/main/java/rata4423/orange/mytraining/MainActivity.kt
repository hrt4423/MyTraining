package rata4423.orange.mytraining

import android.os.Bundle
import android.os.CountDownTimer
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import rata4423.orange.mytraining.ui.theme.MyTrainingTheme
import androidx.lifecycle.viewmodel.compose.viewModel

import rata4423.orange.mytraining.component.Greeting

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
                    HomeScreen()
                }
            }
        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}

@Composable
fun Meter(_progress: Float = 0f) {
    val circleAngle = 360f //円の角度（固定）
    val max = 300f //進捗の最大値
    val angle = 240f //メーターの角度
    val progressWidth = 14.dp //メーターの幅
    val backgroundWidth = 18.dp //外枠を描画する図形の幅
    /*
        描画開始位置の計算
        メーターの角度を変更したくなった場合に、angleさえ変更すれば開始位置を計算してくれる
     */
    val startAngle = (circleAngle / 4) + ((circleAngle - angle) / 2)
    Canvas(
        modifier = Modifier
            .size(150.dp)
            .padding(10.dp),
        onDraw = {
            //メーター:外枠
            drawArc(
                color = Color.Black,
                //描画開始位置を指定（0：右, 90：下, 180：左, 270：上）
                startAngle = startAngle,
                //実際に描画する角度
                sweepAngle = angle,
                useCenter = false,
                size = Size(size.width, size.height),
                style = Stroke(
                    width = backgroundWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )
            //メーター:背景
            drawArc(
                color = Color.DarkGray,
                startAngle = startAngle,
                sweepAngle = angle,
                useCenter = false,
                size = Size(size.width, size.height),
                style = Stroke(width = progressWidth.toPx(), cap = StrokeCap.Round)
            )
            //進捗の描画
            drawArc(
                color = Color.Cyan,
                startAngle = startAngle,
                sweepAngle = angle / max * _progress,
                useCenter = false,
                size = Size(size.width, size.height),
                style = Stroke(
                    width = progressWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }
    )
}

class HomeViewModel: ViewModel() {
    val progressValue = MutableLiveData(0f)
    fun valueUp() {
        val tmp = progressValue.value ?: 0f
        progressValue.value = tmp + 1f
    }
}
//TODO ボタンでタイマーが起動できるようにする
@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    val progressValue: Float by viewModel.progressValue.observeAsState(0f)

    val timer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                viewModel.valueUp()
            }
            override fun onFinish() {
                
            }
        }
    Column(){
        Greeting("Android")

        Meter(progressValue)
        timer.start()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyTrainingTheme {
        Column{
            Greeting("Android")
            Meter(200f)
        }
    }
}
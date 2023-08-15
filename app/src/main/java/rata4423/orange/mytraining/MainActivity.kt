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
import androidx.lifecycle.viewmodel.compose.viewModel

import rata4423.orange.mytraining.ui.theme.MyTrainingTheme

import rata4423.orange.mytraining.components.Greeting
import rata4423.orange.mytraining.components.Meter
import rata4423.orange.mytraining.screen.HomeScreen

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
//TODO ボタンでタイマーが起動できるようにする
//todo ナビゲーション

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyTrainingTheme {
        HomeScreen()
    }
}
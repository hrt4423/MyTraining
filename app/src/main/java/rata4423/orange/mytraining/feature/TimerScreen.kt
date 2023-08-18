package rata4423.orange.mytraining.components

import android.os.CountDownTimer
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.ComposeCompilerApi
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
import rata4423.orange.mytraining.feature.HomeViewModel

import rata4423.orange.mytraining.ui.theme.MyTrainingTheme

class HomeViewModel: ViewModel() {
    val progressValue = MutableLiveData(0f)
    fun valueUp() {
        val tmp = progressValue.value ?: 0f
        progressValue.value = tmp + 1f
    }
}
@Composable
fun TimerScreen(viewModel: HomeViewModel = viewModel()) {
    val progressValue: Float by viewModel.progressValue.observeAsState(0f)

    val timer = object : CountDownTimer(3000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            viewModel.valueUp()
        }
        override fun onFinish() {

        }
    }

    Column() {
        Text(
            text = "TimerScreen"
        )

        Meter(progressValue)

        Text(
            text =  "Value : $progressValue"
        )
    }

    timer.start()
    //常に監視できていない
    if(progressValue >= 300f){
        timer.cancel()
    }
}

@Preview(showBackground = true)
@Composable
fun TimerScreenPreview() {
    MyTrainingTheme {
        TimerScreen()
    }
}

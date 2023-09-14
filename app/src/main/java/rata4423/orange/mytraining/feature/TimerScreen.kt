package rata4423.orange.mytraining.feature

import android.os.CountDownTimer
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import rata4423.orange.mytraining.components.Meter

import rata4423.orange.mytraining.ui.theme.MyTrainingTheme
import java.util.Timer
import kotlin.concurrent.schedule

class TimerScreenViewModel: ViewModel() {
    val progressValue = MutableLiveData(0f)
    fun valueUp() {
        viewModelScope.launch {
            val tmp = progressValue.value ?: 0f
            progressValue.value = tmp + 1f
        }
    }
}

@Composable
fun TimerScreen(viewModel: TimerScreenViewModel = viewModel()) {
    val progressValue: Float by viewModel.progressValue.observeAsState(0f)

    LaunchedEffect(Unit) {
        //画面読込時の処理
        Timer().schedule(0, 1000){
            if(progressValue < 9f) {
                viewModel.valueUp()
            } else {
                this.cancel()
            }
        }
    }

    
//  レイアウト
    Column() {
        Text(
            text = "TimerScreen"
        )

        Meter(progressValue)

        Text(
            text =  "Value : $progressValue"
        )

        Row() {
            Button(onClick = { /*todo*/ }) {
                Text(text = "Start")
            }
            //cancel()が機能していません。スコープの問題？
            Button(onClick = { /*todo*/ }) {
                Text(text = "Stop")
            }
        }
    }

//    timer.start()
    //常に監視できていない
//    if(progressValue >= 300f){
//        timer.cancel()
//    }
}

@Preview(showBackground = true)
@Composable
fun TimerScreenPreview() {
    MyTrainingTheme {
        TimerScreen()
    }
}

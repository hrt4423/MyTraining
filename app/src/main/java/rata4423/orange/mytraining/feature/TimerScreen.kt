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
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import rata4423.orange.mytraining.components.Meter

import rata4423.orange.mytraining.ui.theme.MyTrainingTheme

class TimerScreenViewModel: ViewModel() {
    val progressValue = MutableLiveData(0f)
    fun valueUp() {
        val tmp = progressValue.value ?: 0f
        progressValue.value = tmp + 1f
    }
}

@Composable
fun TimerScreen(viewModel: TimerScreenViewModel = viewModel()) {
    val progressValue: Float by viewModel.progressValue.observeAsState(0f)

    val timer = object : CountDownTimer(10000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            viewModel.valueUp()
        }
        override fun onFinish() {

        }
    }

//    LaunchedEffect(progressValue) {
//        if(progressValue >= 7f){
//            timer.cancel()
//        }
//    }
    //todo: cancel処理を動かす
    LaunchedEffect(Unit) {
        //timer.start()
        //delay(7000)
        //動きはした
        //timer.cancel()
    }


//    val context = LocalContext.current
//    LaunchedEffect(progressValue) {
//        if(progressValue >= 7){
//            timer.cancel()
//            Toast.makeText(context, "canceled", Toast.LENGTH_SHORT).show()
//        }
//    }
    
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
            Button(onClick = { timer.start() }) {
                Text(text = "Start")
            }
            //cancel()が機能していません。スコープの問題？
            Button(onClick = { timer.cancel() }) {
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

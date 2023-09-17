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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import rata4423.orange.mytraining.components.Meter

import rata4423.orange.mytraining.ui.theme.MyTrainingTheme
import java.util.Timer
import java.util.TimerTask
import kotlin.concurrent.schedule

class TimerScreenViewModel: ViewModel() {
    val progressValue = MutableLiveData(10f)
    fun valueUp() {
        viewModelScope.launch {
            val tmp = progressValue.value ?: 0f
            progressValue.value = tmp + 1f
        }
    }
    fun valueDown() {
        viewModelScope.launch {
            val tmp = progressValue.value ?: 0f
            progressValue.value = tmp - 1f
        }
    }
}

//class TestTask : TimerTask() {
//
//    private val viewModel = TimerScreenViewModel()
//
//    override fun run() {
//        viewModel.valueDown()
//    }
//}

@Composable
fun TimerScreen(viewModel: TimerScreenViewModel = viewModel()) {
    val progressValue: Float by viewModel.progressValue.observeAsState(0f)

    //タイマーの監視
    //タイマー用のコルーチン
    val coroutineScope = rememberCoroutineScope()
    //タイマーが実行中かを追跡するフラグ
    val isTimerRunning = remember { mutableStateOf(false) }
    //タイマータスク
    val timerTask = remember { mutableStateOf<TimerTask?>(null)}

    LaunchedEffect(Unit) {

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
            Button(
                onClick = {
                    if (!isTimerRunning.value) {
                        // タイマーが実行中でなければ、新しいタイマーを作成
                        val task = object : TimerTask() {
                            override fun run() {
                                viewModel.valueDown()
                            }
                        }
                        timerTask.value = task

                        //このコルーチンでタイマーを実行
                        coroutineScope.launch {
                            // タイマーを1秒ごとに実行
                            timerTask.value?.let {
                                withContext(Dispatchers.IO) {
                                    Timer().scheduleAtFixedRate(it, 0, 1000)
                                }
                            }
                        }
                        //フラグを更新
                        isTimerRunning.value = true
                    }
                }

            ) {
                Text(text = "Start")
            }
            //cancel()が機能していません。スコープの問題？
            //TODO コルーチンの実装
            Button(
                onClick = {
                    timerTask.value?.cancel()
                    //フラグを更新
                    isTimerRunning.value = false
                }
            ) {
                Text(text = "Stop")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TimerScreenPreview() {
    MyTrainingTheme {
        TimerScreen()
    }
}

package rata4423.orange.mytraining.feature

import android.os.CountDownTimer
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerScreen(viewModel: TimerScreenViewModel = viewModel()) {
    val progressValue: Float by viewModel.progressValue.observeAsState(0f)

//    val coroutineScope = rememberCoroutineScope()
    val isTimerRunning = remember { mutableStateOf(false) }
    val timerTask = remember { mutableStateOf<CountDownTimer?>(null) }

    Column {
        Text(text = "TimerScreen")

        Meter(progressValue)

        Text(text = "Value : $progressValue")

        var text by remember { mutableStateOf("") }

        OutlinedTextField(
            value = text,
            onValueChange = { text =  it }
        )

        Row {
            Button(
                onClick = {
                    if (!isTimerRunning.value) {
                        val task = object : CountDownTimer((progressValue * 1000).toLong(), 1000) {
                            override fun onTick(millisUntilFinished: Long) {
                                viewModel.valueDown()
                            }

                            override fun onFinish() {
                                isTimerRunning.value = false
                            }
                        }
                        timerTask.value = task

                        // タイマータスクを開始
                        task.start()

                        isTimerRunning.value = true
                    }
                }
            ) {
                Text(text = "Start")
            }

            Button(
                onClick = {
                    timerTask.value?.cancel()
                    isTimerRunning.value = false
                }
            ) {
                Text(text = "Cancel")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TimerScreenPreview() {
    MyTrainingTheme {
        //TimerScreen()
    }
}

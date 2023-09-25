package rata4423.orange.mytraining.feature

import android.os.CountDownTimer
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import rata4423.orange.mytraining.components.Meter
import rata4423.orange.mytraining.ui.theme.MyTrainingTheme

class TimerScreenViewModel: ViewModel() {
    val progressValue = MutableLiveData(0f)
    fun setProgressValue(newValue: Int) {
        progressValue.value = newValue.toFloat()
    }
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

        var stringTimerSec by remember { mutableStateOf("") }
        var stringTimerMin by remember { mutableStateOf("") }

        //var checkedStringMin = "0"
        //TODO: キーボードが自動で閉じない
        //TODO: メーターへの入力反映のトリガーをどうするか
        //TODO: 入力フォームを分離（入力時だけ表示など）
        Row {
            OutlinedTextField(
                value = stringTimerMin,
                onValueChange = { stringTimerMin =  it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    
                }

                )
            )
        }

        Row {
            Button(
                onClick = {
//                    viewModel.setProgressValue(stringTimerSec.toFloat())

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
        TimerScreen()
    }
}

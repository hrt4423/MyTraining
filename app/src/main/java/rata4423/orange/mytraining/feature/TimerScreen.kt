package rata4423.orange.mytraining.feature

import android.os.CountDownTimer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredWidth
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    fun addProgressValue(addValue: Int) {
        progressValue.value = progressValue.value?.plus(addValue.toFloat())
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
fun toSec(min: Int): Int {
    return min * 60
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerScreen(viewModel: TimerScreenViewModel = viewModel()) {
    val progressValue: Float by viewModel.progressValue.observeAsState(0f)
    var timerValue: Int by remember { mutableStateOf(0) }

    val isTimerRunning = remember { mutableStateOf(false) }
    val timerTask = remember { mutableStateOf<CountDownTimer?>(null) }

    Column {

        Text(text = "TimerScreen")

        //TODO: 引数に最大値を追加
        Meter(progressValue, timerValue.toFloat())

        Text(text = "Value : $progressValue")

        var stringTimerSec by remember { mutableStateOf("0") }
        var stringTimerMin by remember { mutableStateOf("0") }

        val focusManager = LocalFocusManager.current

        //TODO: 入力フォームを分離（入力時だけ表示など）
        //TODO: 変数の管理（追加、クリアなど）
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .weight(1f)
                    .requiredWidth(150.dp),
                value = stringTimerMin,
                onValueChange = { stringTimerMin =  it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    if(stringTimerMin == "") {
                        //viewModel.setProgressValue(0)
                        timerValue.plus(0)
                    } else {
                        //viewModel.setProgressValue(toSec(Integer.parseInt(stringTimerMin)))
                        timerValue.plus(toSec(Integer.parseInt(stringTimerMin)))
                    }
                    //右のテキストボックスにカーソルを移動
                    focusManager.moveFocus(FocusDirection.Right)
                })
            )
            OutlinedTextField(
                modifier = Modifier
                    .weight(1f)
                    .requiredWidth(150.dp),
                value = stringTimerSec,
                onValueChange = { stringTimerSec =  it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    //TODO 分を加算
                    if(stringTimerMin == "") {
//                        viewModel.setProgressValue(0)
                        timerValue.plus(0)
                    }else{
                        //viewModel.setProgressValue(Integer.parseInt(stringTimerMin))
                        timerValue.plus(Integer.parseInt(stringTimerSec))
                        viewModel.setProgressValue(timerValue)
                    }
                    focusManager.clearFocus()
                })
            )
        }

        Row {
            Button(
                onClick = {
                    if (!isTimerRunning.value) {
                        //タイマーに設定
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

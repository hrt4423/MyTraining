package rata4423.orange.mytraining.screen

import android.os.CountDownTimer
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import rata4423.orange.mytraining.components.Greeting
import rata4423.orange.mytraining.components.Meter
import rata4423.orange.mytraining.ui.theme.MyTrainingTheme
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {
    val progressValue = MutableLiveData(0f)
    fun valueUp() {
        val tmp = progressValue.value ?: 0f
        progressValue.value = tmp + 1f
    }
}

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

    Column() {
        Greeting("Android")
        Meter(progressValue)
        timer.start()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyTrainingTheme {
        HomeScreen()
    }
}
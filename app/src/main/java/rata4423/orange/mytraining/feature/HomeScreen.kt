package rata4423.orange.mytraining.feature

import android.os.CountDownTimer
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
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


@Composable
fun HomeScreen() {
    Text(
        text = "Home"
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyTrainingTheme {
        HomeScreen()
    }
}
package rata4423.orange.mytraining.feature

//import androidx.compose.material3.TimePicker
//import androidx.compose.material3.rememberTimePickerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import rata4423.orange.mytraining.ui.theme.MyTrainingTheme


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
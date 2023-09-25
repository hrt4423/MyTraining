package rata4423.orange.mytraining

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
//import androidx.annotation.Size
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import rata4423.orange.mytraining.feature.TimerScreen
import rata4423.orange.mytraining.ui.theme.MyTrainingTheme
import rata4423.orange.mytraining.feature.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTrainingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "TimerScreen") {
                        composable(route = "TimerScreen") {
                            TimerScreen()
                        }
                    }
                }
            }
        }
    }
}
//TODO ナビゲーション

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyTrainingTheme {
        HomeScreen()
    }
}
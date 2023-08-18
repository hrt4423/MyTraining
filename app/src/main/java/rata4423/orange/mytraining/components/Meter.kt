package rata4423.orange.mytraining.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import rata4423.orange.mytraining.ui.theme.MyTrainingTheme

@Composable
fun Meter(progress: Float = 0f) {
    val circleAngle = 360f //円の角度（固定）
    val max = 300f //進捗の最大値
    val angle = 240f //メーターの角度
    val progressWidth = 14.dp //メーターの幅
    val backgroundWidth = 18.dp //外枠を描画する図形の幅

    //描画開始位置の計算
    //メーターの角度を変更したくなった場合に、angleさえ変更すれば開始位置を計算してくれる
    val startAngle = (circleAngle / 4) + ((circleAngle - angle) / 2)
    Canvas(
        modifier = Modifier
            .size(150.dp)
            .padding(10.dp),
        onDraw = {
            //メーター:外枠
            drawArc(
                color = Color.Black,
                //描画開始位置を指定（0：右, 90：下, 180：左, 270：上）
                startAngle = startAngle,
                //実際に描画する角度
                sweepAngle = angle,
                useCenter = false,
                size = Size(size.width, size.height),
                style = Stroke(
                    width = backgroundWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )
            //メーター:背景
            drawArc(
                color = Color.DarkGray,
                startAngle = startAngle,
                sweepAngle = angle,
                useCenter = false,
                size = Size(size.width, size.height),
                style = Stroke(width = progressWidth.toPx(), cap = StrokeCap.Round)
            )
            //進捗の描画
            drawArc(
                color = Color.Cyan,
                startAngle = startAngle,
                //ゲージが溢れないように
                sweepAngle = if(progress < max) { angle / max * progress } else { angle / max * max },
                useCenter = false,
                size = Size(size.width, size.height),
                style = Stroke(
                    width = progressWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MeterPreview() {
    MyTrainingTheme {
        Meter(180f)
    }
}

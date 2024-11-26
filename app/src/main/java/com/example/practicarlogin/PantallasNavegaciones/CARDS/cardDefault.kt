import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.practicarlogin.R
import com.example.practicarlogin.fuentes.Fuentes

@Composable
fun cargarInformacion(
    titulo: String,
    lock: Boolean,
    image: Painter,
    navigate: () -> Unit,
    componenteSeleccionado: () -> Unit
) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(12.dp))
            .wrapContentHeight()
            .clickable {
                if (!lock) {
                    componenteSeleccionado()
                    ;navigate()
                }
            },
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary,
            contentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        )
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .weight(1f)
                    .padding(15.dp)
            ) {
                Icon(
                    painter = painterResource(
                        if (!lock) {
                            R.drawable.plus_sign
                        } else {
                            R.drawable.lock
                        }
                    ),
                    contentDescription = "",
                    tint = colorResource(R.color.blue)
                )
            }
            Text(
                titulo,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3f),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.inverseSurface,
                fontFamily = Fuentes.mulishSemiBold
            )
            Icon(
                painter = image,
                contentDescription = "",
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp),
                tint = colorResource(R.color.blue)
            )
        }
    }
}
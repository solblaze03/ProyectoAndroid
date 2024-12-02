import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.practicarlogin.R
import com.example.practicarlogin.fuentes.Fuentes
import com.example.practicarlogin.languageSelect
import com.example.practicarlogin.piezas.CPU
import com.example.practicarlogin.piezas.Caja
import com.example.practicarlogin.piezas.Graphic

@Composable
fun cargarCase(
    caja: Caja?,
    function: () -> Unit,
    remplazar: () -> Unit,
    cambiarComponente: () -> Unit,
    lockPsu: () -> Unit
) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(12.dp))
            .wrapContentHeight(),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary,
            contentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        )
    ) {
        Column(modifier = Modifier.padding(8.dp)) {

            Row {
                AsyncImage(
                    model = caja?.imagen,
                    contentDescription = "",
                    modifier = Modifier
                        .size(95.dp)
                        .clip(shape = RoundedCornerShape(5.dp)),
                    contentScale = ContentScale.Crop
                )
                Column(modifier = Modifier.padding(start = 10.dp, end = 8.dp)) {
                    Text(
                        "${caja?.nombre}",
                        color = MaterialTheme.colorScheme.inverseSurface,
                        fontSize = 14.5.sp,
                        fontFamily = Fuentes.mulishBold
                    )
                    Text(
                        "${caja?.precio}€",
                        color = MaterialTheme.colorScheme.inverseSurface,
                        fontSize = 14.5.sp,
                        fontFamily = Fuentes.mulishSemiBold
                    )
                    Row {
                        Text(
                            "Peso: ${caja?.peso}",
                            color = MaterialTheme.colorScheme.inverseSurface,
                            fontSize = 12.sp,
                            fontFamily = Fuentes.mulishRegular,
                            modifier = Modifier.weight(1.1f)
                        )
                        Text(
                            "Marca: ${caja?.marca}",
                            color = MaterialTheme.colorScheme.inverseSurface,
                            fontSize = 12.sp,
                            fontFamily = Fuentes.mulishRegular,
                            modifier = Modifier.weight(0.9f)
                        )

                    }
                    Row {
                        Text(
                            "RGB: ${caja?.rgb}",
                            color = MaterialTheme.colorScheme.inverseSurface,
                            fontSize = 12.sp,
                            fontFamily = Fuentes.mulishRegular,
                            modifier = Modifier.weight(1.1f)
                        )
                        Text(
                            "FANs: ${caja?.ventiladores}w",
                            color = MaterialTheme.colorScheme.inverseSurface,
                            fontSize = 12.sp,
                            fontFamily = Fuentes.mulishRegular,
                            modifier = Modifier.weight(0.9f)
                        )


                    }


                }

            }
            Spacer(modifier = Modifier.padding(3.dp))



            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = { cambiarComponente();remplazar() },
                    modifier = Modifier.weight(5f)
                ) {
                    Text(
                        "Remplazar",
                        fontFamily = Fuentes.mulishBold,
                        color = MaterialTheme.colorScheme.inverseSurface
                    )
                }

                Spacer(modifier = Modifier.padding(5.dp))
                IconButton(
                    onClick = { lockPsu();function() },
                    modifier = Modifier
                        .border(
                            width = 0.8.dp,
                            color = MaterialTheme.colorScheme.inverseSurface,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .weight(1f)
                        .height(37.dp)
                        .width(37.dp),
                    colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.onSecondary),
                    content = {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Quitar", tint = colorResource(R.color.blue)
                        )
                    })
            }


        }


    }
}
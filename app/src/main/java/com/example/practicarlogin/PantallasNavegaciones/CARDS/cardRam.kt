package com.example.practicarlogin.PantallasNavegaciones.CARDS

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
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.practicarlogin.R
import com.example.practicarlogin.fuentes.Fuentes
import com.example.practicarlogin.language.languages
import com.example.practicarlogin.languageSelect
import com.example.practicarlogin.piezas.Board
import com.example.practicarlogin.piezas.RAM

@Composable
fun cargarRAM(
    ramElegida: RAM?,
    BorrarRAM: () -> Unit,
    navigate: () -> Unit,
    cambiarComponente: () -> Unit,
    unlockBoard: () -> Unit,
    lockStorage: () -> Unit,
    boardElegida: Board?
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
                    model = ramElegida?.imagen,
                    contentDescription = "",
                    modifier = Modifier
                        .size(95.dp)
                        .clip(shape = RoundedCornerShape(5.dp)),
                    contentScale = ContentScale.Crop
                )
                Column(modifier = Modifier.padding(start = 10.dp, end = 8.dp)) {
                    Text(
                        "${ramElegida?.nombre}",
                        color = MaterialTheme.colorScheme.inverseSurface,
                        fontSize = 14.5.sp,
                        fontFamily = Fuentes.mulishBold
                    )

                    Row {
                        Text(
                            "Cantidad: ${ramElegida?.cantidad}GB",
                            color = MaterialTheme.colorScheme.inverseSurface,
                            fontSize = 12.sp,
                            fontFamily = Fuentes.mulishRegular,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            "${languageSelect.reloj}: ${ramElegida?.velocidad}MHz",
                            color = MaterialTheme.colorScheme.inverseSurface,
                            fontSize = 12.sp,
                            fontFamily = Fuentes.mulishRegular,
                            modifier = Modifier.weight(1.1f)
                        )

                    }
                    Row {
                        Text(
                            "" +
                                    "Tipo: ${ramElegida?.tipoMemoria}",
                            color = MaterialTheme.colorScheme.inverseSurface,
                            fontSize = 12.sp,
                            fontFamily = Fuentes.mulishRegular,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            "${ramElegida?.marca}",
                            color = MaterialTheme.colorScheme.inverseSurface,
                            fontSize = 12.sp,
                            fontFamily = Fuentes.mulishRegular,
                            modifier = Modifier.weight(1.1f)
                        )


                    }
                    Row (modifier = Modifier){
                        Text("Precio:",
                            color = MaterialTheme.colorScheme.inverseSurface,
                            fontSize = 16.sp,
                            fontFamily = Fuentes.mulishBold,)
                        Text(
                            "${ramElegida?.precio}€",
                            color = MaterialTheme.colorScheme.inverseSurface,
                            fontSize = 16.sp,
                            fontFamily = Fuentes.mulishBold,
                            textAlign = TextAlign.End,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 28.dp)
                        )
                    }


                }

            }



            var cont by rememberSaveable{ mutableStateOf(1) }
            Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){

            }
            //Agregar
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(
                    onClick = {if(cont > 1){cont--}},
                    modifier = Modifier.size(30.dp),
                    colors = IconButtonDefaults.outlinedIconButtonColors(containerColor = colorResource(R.color.blue)),
                    content = { Icon(painter = painterResource(R.drawable.minus), contentDescription = "",
                        Modifier.size(15.dp), tint = Color.White)
                    }
                )
                Spacer(Modifier.padding(4.8.dp))
                Text(text =  "$cont", fontSize = 25.sp, color = MaterialTheme.colorScheme.inverseSurface)
                Spacer(Modifier.padding(4.8.dp))
                IconButton(
                    onClick = {if(cont < boardElegida?.slotsRam!!) cont++},
                    modifier = Modifier.size(30.dp),

                    colors = IconButtonDefaults.outlinedIconButtonColors(containerColor = colorResource(R.color.blue)),
                    content = { Icon(imageVector = Icons.Default.Add, contentDescription = "", tint = Color.White) }
                )
                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedButton(
                    onClick = { cambiarComponente();navigate() },
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
                    // lockRAM();unlockProcessor();BorrarBoard()
                    onClick = { lockStorage(); unlockBoard();BorrarRAM() },
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

            //Cantidad






        }


    }
}

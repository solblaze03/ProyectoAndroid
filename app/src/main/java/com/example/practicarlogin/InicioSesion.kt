package com.example.practicarlogin

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicarlogin.`view-model`.LoginState
import com.example.practicarlogin.`view-model`.LoginViewModel
import com.example.practicarlogin.fuentes.Fuentes
import com.example.practicarlogin.language.StringsEnglish
import com.example.practicarlogin.language.StringsSpanish
import com.example.practicarlogin.language.StringsValenciano
import com.example.practicarlogin.language.languages
import com.example.practicarlogin.shared.prefs
import com.google.firebase.auth.FirebaseAuth


lateinit var languageSelect: languages
private val spanish = StringsSpanish
private val english = StringsEnglish
private val valenciano = StringsValenciano


@Composable
fun Login(
    viewModel: LoginViewModel,
    principal: () -> Unit,
    auth: FirebaseAuth,
    registrar: () -> Unit
) {

    val context = LocalContext.current
    val user: String by viewModel.user.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val verificar by viewModel.verificado.observeAsState(false)
    val loginState by viewModel.loginState.observeAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(start = 20.dp, end = 20.dp)

    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val seleccionIdioma: List<languages> = listOf(spanish, english, valenciano)
            if (prefs.prefs.obtenerIdioma() != -1) {

                languageSelect = seleccionIdioma[prefs.prefs.obtenerIdioma()]
            } else {
                languageSelect = seleccionIdioma[0]
            }
            cambioIdioma()
            cargarImagenLogo(languageSelect.inicioSesion)
            Spacer(modifier = Modifier.padding(11.dp))
            cargarTextfieldUser(user) { viewModel.inicioSesion(it, password) }
            Spacer(modifier = Modifier.padding(11.dp))
            cargarTextPassword(password,{ viewModel.inicioSesion(user, it) },loginState,"Email o contraseña incorrectas.",true)
            Spacer(modifier = Modifier.padding(1.dp))
            checkBox()
            Spacer(modifier = Modifier.padding(8.dp))
            Boton({ viewModel.verificar(user, password, context) { principal() } }, verificar)
            Spacer(modifier = Modifier.padding(8.dp))
            oIniciar()
            Spacer(modifier = Modifier.padding(8.dp))
            continuarGoogle(registrar)


        }

    }
}


@Composable
fun cambioIdioma() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 8.dp),
        horizontalArrangement = Arrangement.End
    ) {

        val context = LocalContext.current


        val color = colorResource(R.color.azulIntel)

        iconoBandera(
            painterResource(R.drawable.valencia),
            { prefs.prefs.guardarIdioma(2); (context as Activity).recreate() })



        Text(
            " / ",
            color = MaterialTheme.colorScheme.inverseSurface
        )
        iconoBandera(
            painterResource(R.drawable.espana),
            { prefs.prefs.guardarIdioma(0); (context as Activity).recreate() })
        Text(
            " / ",
            color = MaterialTheme.colorScheme.inverseSurface
        )
        iconoBandera(
            painterResource(R.drawable.britanica),
            { prefs.prefs.guardarIdioma(1); (context as Activity).recreate() })
    }
}

@Composable
fun cargarImagenLogo(text: String) {

    Image(
        painter = painterResource(R.drawable.logo),
        contentDescription = "logo",
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
    )



    Text(
        text,
        fontSize = 34.sp,
        fontFamily = Fuentes.mulishBold,
        color = MaterialTheme.colorScheme.inverseSurface
    )
}

@Composable
fun iconoBandera(imagen: Painter, accion: () -> Unit) {
    Image(
        painter = imagen,
        contentDescription = "",
        modifier = Modifier
            .width(35.dp)
            .height(25.dp)
            .clickable { accion() })

}

@Composable
fun cargarTextfieldUser(user: String, cambioTextField: (String) -> Unit) {
    Text(
        text = languageSelect.nameUser,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp),
        textAlign = TextAlign.Left,
        fontFamily = Fuentes.mulishSemiBold,
        color = MaterialTheme.colorScheme.inverseSurface
    )
    OutlinedTextField(
        value = user,
        onValueChange = { e -> cambioTextField(e) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        singleLine = true,
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.user),
                contentDescription = "iconUser",
                modifier = Modifier.size(25.dp),
                tint = colorResource(R.color.blue)
            )
        },
        placeholder = {
            Text(
                languageSelect.promptUser,
                fontFamily = Fuentes.mulishRegular,
                color = Color.Gray
            )

        }
    )
}

@Composable
fun cargarTextPassword(password: String, cambioTextField: (String) -> Unit, loginState: LoginState?,errorMessage : String,olvPass: Boolean) {


    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = languageSelect.password,
            modifier = Modifier
                .padding(start = 5.dp),
            color = MaterialTheme.colorScheme.inverseSurface
        )
        if(olvPass) {
            Text(
                text = languageSelect.olvPassword,
                modifier = Modifier.padding(end = 8.dp),
                fontSize = 13.5.sp,
                fontFamily = Fuentes.mulishSemiBold,
                color = colorResource(R.color.blue),
            )
        }
    }

    OutlinedTextField(
        value = password,
        onValueChange = { e -> cambioTextField(e) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        singleLine = true,
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.password),
                contentDescription = "iconKey",
                modifier = Modifier.size(25.dp),
                tint = colorResource(R.color.blue)
            )
        },
        placeholder = {
            Text(
                text = languageSelect.inPass,
                fontFamily = Fuentes.mulishRegular,
                color = Color.Gray
            )
        },
        visualTransformation = PasswordVisualTransformation()
    )
    when (loginState ?: LoginState.Idle) {
        is LoginState.Idle -> {

        }

        is LoginState.Success -> {
            //Cambia ventana desde el viewmodel
        }

        is LoginState.Error -> {
            Text(
                errorMessage,
                color = Color.Red,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp),
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun checkBox() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = true,
            onCheckedChange = {},
            colors = CheckboxDefaults.colors(checkedColor = colorResource(R.color.blue))
        )

    Text(
            text = languageSelect.inicioAut,
            fontSize = 14.sp,
            fontFamily = Fuentes.mulishRegular,
            color = Color.Gray
        )
    }
}

@Composable
fun Boton(Verificar: () -> Unit, verificar: Boolean?) {
    Button(
        onClick = { Verificar() },
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        enabled = verificar ?: false,
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.blue))
    ) {
        Text(
            text = languageSelect.inicioSesion, fontFamily = Fuentes.mulishSemiBold,
            color = Color.White
        )
    }
}

@Composable
fun oIniciar() {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        HorizontalDivider(modifier = Modifier.weight(1f))
        Text(
            text = languageSelect.oIniciar,
            modifier = Modifier.padding(start = 10.dp, end = 10.dp),
            fontFamily = Fuentes.mulishSemiBold,
            color = MaterialTheme.colorScheme.inverseSurface
        )
        HorizontalDivider(modifier = Modifier.weight(1f))
    }
}

@Composable
fun continuarGoogle(registrar: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = {},
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.gray))

        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Image(
                    painter = painterResource(R.drawable.google),
                    contentDescription = "Google Icon",
                    modifier = Modifier.size(30.dp)
                )


                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    text = languageSelect.google,
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontFamily = Fuentes.mulishSemiBold
                )
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            text = languageSelect.crear,
            fontFamily = Fuentes.mulishBold,
            color = colorResource(R.color.blue),
            fontSize = 18.sp,
            textDecoration = TextDecoration.Underline
            ,modifier = Modifier.clickable{ registrar()}

        )
    }
}


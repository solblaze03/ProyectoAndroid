package com.example.practicarlogin


import android.util.Log
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicarlogin.`view-model`.LoginViewModel
import com.example.practicarlogin.fuentes.Fuentes
import com.example.practicarlogin.language.StringsEnglish
import com.example.practicarlogin.language.StringsSpanish
import com.example.practicarlogin.language.StringsValenciano
import com.example.practicarlogin.language.languages
import com.example.practicarlogin.shared.prefs
import com.google.firebase.auth.FirebaseAuth


private val spanish = StringsSpanish
private val english = StringsEnglish
private val valenciano = StringsValenciano


@Composable
fun PantallaRegistrar(
    viewModel: LoginViewModel,
    auth: FirebaseAuth,
    iniciarSesion: () -> Unit,
    Principal: () -> Unit
) {

    val context = LocalContext.current
    val user: String by viewModel.registrarUser.observeAsState(initial = "")
    val password: String by viewModel.registrarPass.observeAsState(initial = "")
    val verificar by viewModel.verificado.observeAsState(false)
    val loginState by viewModel.loginState.observeAsState()
    val enableButton by viewModel.verificadoRegistro.observeAsState()
    val createState by viewModel.CreateState.observeAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(start = 20.dp, end = 20.dp)

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
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
            cargarImagenLogo(languageSelect.registrar)
            Spacer(modifier = Modifier.padding(11.dp))
            cargarTextfieldUser(user) { viewModel.registroSesion(it, password) }
            Spacer(modifier = Modifier.padding(11.dp))
            cargarTextPassword(password, { viewModel.registroSesion(user, it) }, createState,"Correo existente",false)
            Spacer(modifier = Modifier.padding(8.dp))
            Seguridad(password, viewModel)
            Spacer(modifier = Modifier.padding(8.dp))
            BotonRegistro(enableButton) {viewModel.CrearCuenta(user,password,Principal)}
            Spacer(modifier = Modifier.padding(8.dp))
            oIniciar()
            Spacer(modifier = Modifier.padding(8.dp))
            IniciarSesion(iniciarSesion)


        }

    }


}

@Composable
fun BotonRegistro(enableButton: Boolean?, Registrarse: () -> Unit) {
    Button(
        onClick = { Registrarse()},
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        enabled = enableButton ?: false,
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.blue))
    ) {
        Text(
            text = languageSelect.registrar, fontFamily = Fuentes.mulishSemiBold,
            color = Color.White
        )

    }
}

@Composable
fun Seguridad(password: String, viewModel: LoginViewModel) {
    val security by viewModel.security.observeAsState(0)

    if (password.isNotEmpty()) {
        viewModel.setSecurity(1)
        if (password.length >= 6) {
            viewModel.setSecurity(2)
            if (password.any { it.isUpperCase() } || password.any { !it.isLetterOrDigit() }) {
                viewModel.setSecurity(3)
            } else {
                viewModel.setSecurity(2)
            }
        } else {
            viewModel.setSecurity(1)
        }
    } else {
        viewModel.setSecurity(0)
    }
    Row(modifier = Modifier.fillMaxWidth()) {


        Log.i("Security", "$security")



        HorizontalDivider(
            thickness = 10.dp, color = (if (security == 0) {
                Color.Gray
            } else if (security == 1) {
                Color.Red
            } else if (security == 2) {
                Color.Yellow
            } else {
                Color.Green
            }), modifier = Modifier
                .weight(1f)
                .clip(
                    RoundedCornerShape(12.dp)
                )
        )
        Spacer(modifier = Modifier.weight(0.1f))
        HorizontalDivider(
            thickness = 10.dp, color = if (security == 2) {
                Color.Yellow
            } else if (security == 3) {
                Color.Green
            } else {
                Color.Gray
            }, modifier = Modifier
                .weight(1f)
                .clip(
                    RoundedCornerShape(12.dp)
                )
        )
        Spacer(modifier = Modifier.weight(0.1f))
        HorizontalDivider(
            thickness = 10.dp, color = if (security == 3) {
                Color.Green
            } else {
                Color.Gray
            }, modifier = Modifier
                .weight(1f)
                .clip(
                    RoundedCornerShape(12.dp)
                )
        )
    }
}

@Composable
fun IniciarSesion(iniciarSesion: () -> Unit) {
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
            text = "¿Tienes cuenta?",
            fontFamily = Fuentes.mulishBold,
            color = colorResource(R.color.blue),
            fontSize = 18.sp,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable { iniciarSesion()}

        )
    }
}

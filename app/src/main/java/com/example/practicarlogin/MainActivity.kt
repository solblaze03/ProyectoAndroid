package com.example.practicarlogin
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
import com.example.practicarlogin.navigation.NavigationWrapper
import com.example.practicarlogin.ui.theme.PracticarLoginTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticarLoginTheme {
                FirebaseApp.initializeApp(LocalContext.current)
                        //bd()

                NavigationWrapper()
            }
        }
    }
}

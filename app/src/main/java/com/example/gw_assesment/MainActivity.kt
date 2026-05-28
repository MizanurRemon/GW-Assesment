package com.example.gw_assesment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.gw_assesment.navigation.AppNavigation
import com.example.gw_assesment.ui.theme.GWAssesmentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GWAssesmentTheme {
                AppNavigation()
                //SetStatusBarColor()
            }
        }
    }
}
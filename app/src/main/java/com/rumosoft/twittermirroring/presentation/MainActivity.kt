package com.rumosoft.twittermirroring.presentation

import android.R.color
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.rumosoft.library_components.presentation.theme.TwitterMirroringTheme
import com.rumosoft.twittermirroring.presentation.navigation.NavigationHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        updateBarsColour()

        setContent {
            TwitterMirroringTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TwitterCloneApp()
                }
            }
        }
    }

    // Needed cause otherwise is using the values of the splash theme
    private fun updateBarsColour() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, color.white)
        window.navigationBarColor = ContextCompat.getColor(this, color.white)
    }
}

@Composable
fun TwitterCloneApp() {
    val navController = rememberNavController()

    NavigationHost(navController)
}

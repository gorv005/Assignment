package com.tcs.assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gaur.blogappyt.screens.home.HomeScreen
import com.tcs.assignment.navigation.NavigationItem
import com.tcs.assignment.ui.theme.BlogAppYTTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlogAppYTTheme {
                // A surface container using the 'background' color from the theme
                androidx.compose.material.Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = androidx.compose.material.MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    MyApp {

                        NavHost(
                            navController = navController,
                            startDestination = NavigationItem.Home.route
                        ) {

                            composable(NavigationItem.Home.route) {
                                HomeScreen(navController = navController)
                            }

                        }

                    }
                }
            }
        }
    }
}


@Composable
fun MyApp(content: @Composable () -> Unit) {
    content()
}




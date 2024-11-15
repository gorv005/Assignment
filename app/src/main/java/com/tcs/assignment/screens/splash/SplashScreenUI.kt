package com.tcs.assignment.screens.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tcs.assignment.R
import com.tcs.assignment.navigation.Screens
import com.tcs.assignment.ui.theme.Purple40
import kotlinx.coroutines.delay

@Composable
fun SplashScreenUI(
    navController: NavController = rememberNavController()
) {
    LaunchedEffect(Unit) {
        delay(2000)
        gotoHome(navController)
    }
    DrawSplashScreen()
}

fun gotoHome(navController: NavController) {
    navController.navigate(Screens.BlogListScreen.name) {
        popUpTo(Screens.SplashScreen.name) { inclusive = true }
    }
}

@Composable
private fun DrawSplashScreen() {
    Scaffold {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                color = Purple40,
                style = MaterialTheme.typography.headlineLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSplashScreen() {
    SplashScreenUI()
}
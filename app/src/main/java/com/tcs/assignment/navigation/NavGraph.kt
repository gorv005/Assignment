package com.tcs.assignment.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tcs.assignment.screens.home.HomeScreen
import com.tcs.assignment.screens.details.BlogDetails
import com.tcs.assignment.screens.splash.SplashScreenUI
import com.tcs.common.Constant.BLOG_ID

@Composable
fun NavGraph() {
    val productDetailsArguments = listOf(
        navArgument(BLOG_ID) {
            type = NavType.StringType
            defaultValue = ""
        },
    )
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.name,
    ) {

        composable(route = Screens.SplashScreen.name) {
            SplashScreenUI(
                navController = navController
            )
        }
        composable(route = Screens.BlogListScreen.name) {
            HomeScreen(
                navController = navController
            )
        }

        composable(
            route = Screens.DetailsScreen.name.plus("/{$BLOG_ID}"),
            arguments = productDetailsArguments
        ) { entry ->

            var id = entry.arguments?.getString(BLOG_ID)
            BlogDetails(
                navController = navController, id
            )
        }
    }
}

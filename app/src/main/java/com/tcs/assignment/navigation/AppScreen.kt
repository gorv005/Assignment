package com.tcs.assignment.navigation

sealed class AppScreen(val route: String) {
    object SplashScreen : AppScreen(ConstantAppScreenName.SPLASH_SCREEN)
    object BlogListScreen : AppScreen(ConstantAppScreenName.BLOG_LIST_SCREEN)
    object DetailsScreen : AppScreen(ConstantAppScreenName.DETAILS_SCREEN)

}


object ConstantAppScreenName {
    const val BLOG_LIST_SCREEN = "blog_screen"
    const val DETAILS_SCREEN = "details_screen"
    const val SPLASH_SCREEN = "splash_screen"

}
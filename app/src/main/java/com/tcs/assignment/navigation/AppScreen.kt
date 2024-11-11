package com.tcs.assignment.navigation

sealed class AppScreen(val route: String) {
    object BlogListScreen : AppScreen(ConstantAppScreenName.BLOG_LIST_SCREEN)
    object DetailsScreen : AppScreen(ConstantAppScreenName.DETAILS_SCREEN)

}


object ConstantAppScreenName {
    const val BLOG_LIST_SCREEN = "product_screen"
    const val DETAILS_SCREEN = "details_screen"

}
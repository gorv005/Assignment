package com.tcs.assignment.navigation

sealed class NavigationItem(val route:String){

    object Home: NavigationItem("Home")
    object DetailsScreen:NavigationItem("Details")

}

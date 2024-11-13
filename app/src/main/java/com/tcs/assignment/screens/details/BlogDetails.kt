package com.tcs.assignment.screens.details

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tcs.assignment.screens.details.viewmodel.BlogDetailsViewModel
import com.tcs.assignment.screens.home.PostItem

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BlogDetails(
    navController: NavController,
    blogID: String? = null
    ){
   val detailsViewModel: BlogDetailsViewModel = hiltViewModel()
    LaunchedEffect(true) {
        detailsViewModel.getBlogDetails(blogID!!)
    }

    val res = detailsViewModel.details.collectAsState().value

    if (res.isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

    if (res.error.isNotBlank()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = res.error.toString(), modifier = Modifier.align(Alignment.Center))
        }
    }

    res.data?.let {
        SideEffect {
        Log.d("DATA=",res.data.toString())
        }
        Column(modifier = Modifier) {
            PostItem(it = it, onClick = {})
            Text(text = it.likes.toString() + " Likes", modifier = Modifier.padding(12.dp))
            FlowRow {
                it.tags.forEach {
                    TagItem(it = it)
                }
            }

        }


    }
}

@Composable
fun TagItem(it: String) {

    Card(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(8.dp), shape = RoundedCornerShape(
            40.dp
        ),
        border = BorderStroke(0.5.dp, color = Color.Gray)
    ) {
        Text(text = it, style = TextStyle(color = Color.Black), modifier = Modifier.padding(12.dp))
    }

}
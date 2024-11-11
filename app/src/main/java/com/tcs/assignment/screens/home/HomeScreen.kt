package com.tcs.assignment.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberImagePainter
import com.tcs.assignment.R
import com.tcs.assignment.navigation.AppScreen
import com.tcs.assignment.screens.home.viewmodel.HomeViewModel
import com.tcs.assignment.util.ErrorMessage
import com.tcs.assignment.util.LoadingNextPageItem
import com.tcs.assignment.util.PageLoader
import com.tcs.assignment.util.onClick
import com.tcs.domain.model.Blog


@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {


    val list = viewModel.pager.collectAsLazyPagingItems()

    LazyColumn {
        items(list.itemCount) {
            PostItem(it = list[it]!!,
                onClick = { index ->
                    navController.navigate(AppScreen.DetailsScreen.route.plus("/${index}"))
                })
        }
        list.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        PageLoader(
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }

                loadState.refresh is LoadState.Error -> {
                  //  val error = list.loadState.refresh as LoadState.Error
                    item {
                        ErrorMessage(
                            modifier = Modifier.fillMaxWidth(),
                            message = stringResource(id = R.string.blog_list_load_error),
                            onClickRetry = { retry() })
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item { LoadingNextPageItem(modifier = Modifier) }
                }

                loadState.append is LoadState.Error -> {
                  //  val error = list.loadState.refresh as LoadState.Error
                    item {
                        ErrorMessage(
                            modifier = Modifier,
                            message = stringResource(id = R.string.blog_list_load_error),
                            onClickRetry = { retry() })
                    }
                }
            }
        }
    }

}

@Composable
fun PostItem(it: Blog, onClick: ( String) -> Unit) {

    Column(modifier = Modifier.fillMaxWidth().clickable { onClick(it.id!!) }, verticalArrangement = Arrangement.Center) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), verticalAlignment = Alignment.CenterVertically
        ) {

            CircularImage(50.0, 50.0, 25.0, it.owner.picture)

            Spacer(modifier = Modifier.width(6.dp))

            Text(text = "${it.owner.firstName} ${it.owner.lastName}")

        }

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            painter = rememberImagePainter(data = it.image), contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Text(
            text = it.text,
            modifier = Modifier.padding(12.dp),
            style = TextStyle(color= Color.Gray, fontSize = 20.sp)
        )

        Divider()


    }

}
@Composable
fun CircularImage(width: Double, height: Double, radius: Double, imageUrl: String) {

    Card(
        modifier = Modifier
            .width(width = width.dp)
            .height(height = height.dp), shape = RoundedCornerShape(radius.dp)
    ) {

        Image(
            painter = rememberImagePainter(data = imageUrl), contentDescription = null,
            contentScale = ContentScale.Crop
        )

    }


}


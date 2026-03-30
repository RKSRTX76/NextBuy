package com.rksrtx76.nextbuy.presentation.homescreen

import android.R.attr.right
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.rksrtx76.nextbuy.domain.model.Product
import com.rksrtx76.nextbuy.presentation.components.BottomNavItem
import com.rksrtx76.nextbuy.presentation.components.BottomNavigationBar
import com.rksrtx76.nextbuy.presentation.homescreen.components.BannerPager
import com.rksrtx76.nextbuy.presentation.homescreen.components.CategorySection
import com.rksrtx76.nextbuy.presentation.homescreen.components.FilterRow
import com.rksrtx76.nextbuy.presentation.homescreen.components.ProductCard
import com.rksrtx76.nextbuy.presentation.homescreen.components.SearchBar
import com.rksrtx76.nextbuy.presentation.homescreen.components.TopAppBar
import com.rksrtx76.nextbuy.presentation.navigation.Routes
import com.rksrtx76.nextbuy.util.Result
import kotlin.math.roundToInt

@Composable
fun HomeScreen(
    navController: NavHostController,
    productViewModel: ProductViewModel = hiltViewModel()
) {

    val productState by productViewModel.productState.collectAsState()
    val profilePhotoUrl by productViewModel.profilePhotoUrl.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    var showCategoryFilter by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(profilePhotoUrl = profilePhotoUrl)
        },
        bottomBar = {
            BottomNavigationBar(
                currRoute = Routes.HomeScreen,
                onItemClick = { item ->
                    when(item){
                        BottomNavItem.Cart -> {
                            navController.navigate(Routes.CartScreen)
                        }
                        BottomNavItem.Home -> {}
                        BottomNavItem.Search -> {
                            navController.navigate(Routes.SearchScreen)
                        }
                        BottomNavItem.Profile -> {
                            navController.navigate(Routes.ProfileScreen)
                        }
                        BottomNavItem.Wishlist -> {
                            navController.navigate(Routes.WishlistScreen)
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFf9f9f9))
        ) {

            when(val state = productState){
                is Result.Loading ->{
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        CircularProgressIndicator(
                            strokeWidth = 2.dp
                        )
                    }
                }
                is Result.Error ->{
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Error : ${state.message}",
                                color = Color.Red
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            TextButton(
                                onClick = {
                                    productViewModel.retryLoading()
                                }
                            ) {
                                Text("Retry")
                            }
                        }
                    }
                }
                is Result.Success ->{
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            SearchBar(
                                searchQuery = searchQuery,
                                navController = navController
                            )
                        }

                        item(span = { GridItemSpan(maxLineSpan) }) {
                            Spacer(modifier = Modifier.height(4.dp))

                            BannerPager()
                        }

                        item(span = { GridItemSpan(maxLineSpan) }) {
                            FilterRow(
                                showCategoryFilter = showCategoryFilter,
                                onCategoryClick = {
                                    showCategoryFilter = !showCategoryFilter
                                }
                            )
                        }

                        if(showCategoryFilter){
                            item(span = { GridItemSpan(maxLineSpan) }) {
                                CategorySection(productViewModel = productViewModel)
                            }
                        }

                        items(state.data){ product ->
                            ProductCard(
                                product = product,
                                onClick = {
                                    navController.navigate(Routes.ProductDetailScreen(product.id))
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}







//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun HomeScreenPrev(){
//    HomeScreen()
//}
package com.rksrtx76.nextbuy.presentation.detailscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.rksrtx76.nextbuy.presentation.cart.CartViewModel
import com.rksrtx76.nextbuy.presentation.homescreen.ProductViewModel
import com.rksrtx76.nextbuy.presentation.navigation.Routes
import com.rksrtx76.nextbuy.presentation.wishlist.WishlistViewModel
import com.rksrtx76.nextbuy.util.Result
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    productId : Int,
    navController : NavHostController,
    productViewModel: ProductViewModel = hiltViewModel(),
    wishlistViewModel: WishlistViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel()
){
    val productState by productViewModel.productState.collectAsState()
    var selectedImageIndex by remember { mutableStateOf(0) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var isFavorite by remember { mutableStateOf(false) }
    val currentProduct by productViewModel.selectedProduct.collectAsState()
    // check if product is in wishlist
    LaunchedEffect(productId,productState) {
        isFavorite = wishlistViewModel.isInWishlist(productId)
        if(productState is Result.Success){
            productViewModel.findProductById(productId)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Product Details")},
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            currentProduct?.let { product->
                                shareProduct(product,context)
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share"
                        )
                    }
                    IconButton(
                        onClick = {
                            // favorite logic
                            currentProduct?.let { product ->
                                scope.launch {
                                    if(isFavorite){
                                        isFavorite = false
                                        wishlistViewModel.removeFromWishlist(product.id)
                                    }else{
                                        isFavorite = true
                                        wishlistViewModel.addToWishlist(product)
                                    }
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if(isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = if(isFavorite) Color.Red else Color.Gray.copy(alpha = 0.75f)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        when(val state = productState){
            is Result.Loading ->{
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator()
                }
            }
            is Result.Error ->{
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text("Product not found")
                }
            }
            is Result.Success ->{
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .verticalScroll(rememberScrollState())
                ) {
                    currentProduct?.let { product ->
                        ProductImageGallery(
                            product = product,
                            selectedImageIndex = selectedImageIndex,
                            onImageClick = {
                                selectedImageIndex = it
                            }
                        )

                        ProductDetails(product = product)

                        AddToCartSection(
                            product = product,
                            onAddToCart = {
                                product?.let { item->
                                    cartViewModel.addToCart(product = item, 1)
                                }
                            },
                            onGoToCart = {
                                navController.navigate(Routes.CartScreen)
                            }
                        )
                    }
                }
            }

        }
    }


}
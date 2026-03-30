package com.rksrtx76.nextbuy.presentation.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.rksrtx76.nextbuy.R
import com.rksrtx76.nextbuy.domain.model.CartItem
import com.rksrtx76.nextbuy.presentation.navigation.Routes
import kotlin.compareTo
import kotlin.math.roundToInt
import kotlin.times
import kotlin.toString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    navController : NavHostController,
    cartViewModel: CartViewModel = hiltViewModel()
){
    val state by cartViewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "My Cart",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
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
                    if (state.cartItems.isNotEmpty()){
                        TextButton(
                            onClick = {
                                cartViewModel.clearCart()
                            }
                        ) {
                            Text(
                                text = "Clear All",
                                color = Color(0xFFF83758),
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Black
                )
            )
        },
        bottomBar = {
            if(state.cartItems.isNotEmpty()){
                CartBottomBar(
                    totalPrice = state.totalPrice,
                    totalItems = state.totalItems,
                    onCheckout = {
                        // Navigate to checkout screen
                    }
                )
            }
        },
        modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
    ){ paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF8F8F8))
        ){
            when{
                state.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                state.error != null -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = state.error ?: "An error occurred",
                            color = Color.Red,
                            fontSize = 16.sp
                        )
                    }
                }
                state.cartItems.isEmpty() ->{
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){
                        Text(
                            text = "Your cart is empty",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Add products to your cart to see them here",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(
                            onClick = { navController.navigate(Routes.HomeScreen) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFF83758)
                            )
                        ) {
                            Text("Browse Products")
                        }
                    }
                }
                else ->{
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(state.cartItems){ item->
                            CartItemCard(
                                cartItem = item,
                                onIncrease = {
                                    cartViewModel.updateQuantity(item.product.id, item.quantity + 1)
                                },
                                onDecrease = {
                                    if(item.quantity > 1){
                                        cartViewModel.updateQuantity(item.product.id, item.quantity - 1)
                                    }
                                },
                                onDelete = {
                                    cartViewModel.removeFromCart(item.product.id)
                                },
                                onClick = {
                                    navController.navigate(Routes.ProductDetailScreen(item.product.id))
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CartItemCard(
    cartItem : CartItem,
    onIncrease : () -> Unit,
    onDecrease : () -> Unit,
    onDelete : () -> Unit,
    onClick : () -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(cartItem.product.thumbnail)
                    .crossfade(true)
                    .build(),
                contentDescription = cartItem.product.title,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF5F5F5)),
                contentScale = ContentScale.Crop,
                loading = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            strokeWidth = 2.dp
                        )
                    }
                },
                error = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFF5F5F5)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No Image",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Product Details
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ){
                // Title
                Text(
                    text = cartItem.product.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Brand
                if(cartItem.product.brand.isNotEmpty()){
                    Text(
                        text = cartItem.product.brand,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "₹${(cartItem.product.discountedPrice * cartItem.quantity).roundToInt()}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    if (cartItem.product.discountPercentage > 0) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "₹${(cartItem.product.price * cartItem.quantity).roundToInt()}",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            textDecoration = TextDecoration.LineThrough
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        // Off price
                        Text(
                            text = "₹${((cartItem.product.price - cartItem.product.discountedPrice) * cartItem.quantity).roundToInt()} Off",
                            fontSize = 14.sp,
                            color = Color(0xFFFF6200)
                        )

                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Quantity Controls
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(Color(0xFFF0F0F0), RoundedCornerShape(8.dp))
                            .padding(4.dp)
                    ) {
                        IconButton(
                            onClick = {
                                if(cartItem.quantity == 1){
                                    onDelete()
                                }else{
                                    onDecrease()
                                }
                            },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Image(
                                imageVector = Icons.Default.Remove,
                                contentDescription = "Decrease",
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(2.dp))

                        Text(
                            text = cartItem.quantity.toString(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(horizontal = 12.dp)
                        )

                        Spacer(modifier = Modifier.width(2.dp))

                        IconButton(
                            onClick = onIncrease,
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Increase",
                                tint = Color.Black,
                                modifier = Modifier.size(18.dp)
                            )
                        }

                    }

                    // Remove Button
                    IconButton(
                        onClick = onDelete,
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Remove",
                            tint = Color(0xFFF83758),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }




    }
}


@Composable
fun CartBottomBar(
    totalPrice : Double,
    totalItems : Int,
    onCheckout : () -> Unit
){
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
        shadowElevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Column {
                    Text(
                        text = "Total ($totalItems ${if (totalItems == 1) "item" else "items"})",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "₹${totalPrice.roundToInt()}",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
                Button(
                    onClick = onCheckout,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF83758)
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .height(56.dp)
                        .width(160.dp)
                ) {
                    Text(
                        text = "Checkout",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
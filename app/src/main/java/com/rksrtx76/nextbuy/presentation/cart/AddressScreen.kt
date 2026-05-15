//package com.rksrtx76.nextbuy.presentation.cart
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.WindowInsets
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.navigationBars
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.layout.windowInsetsPadding
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.ArrowBack
//import androidx.compose.material.icons.filled.Add
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.material3.TopAppBar
//import androidx.compose.material3.TopAppBarDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.SpanStyle
//import androidx.compose.ui.text.buildAnnotatedString
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.text.withStyle
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavHostController
//import com.rksrtx76.nextbuy.presentation.userprofile.UserProfileViewModel
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AddressScreen(
//    navController : NavHostController,
//    viewModel: UserProfileViewModel = hiltViewModel()
//) {
//    val uiState by viewModel.state.collectAsState()
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(
//                        text = "My Cart",
//                        fontWeight = FontWeight.Bold,
//                        fontSize = 20.sp
//                    )
//                },
//                navigationIcon = {
//                    IconButton(
//                        onClick = {
//                            navController.navigateUp()
//                        }
//                    ) {
//                        Icon(
//                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                            contentDescription = "Back"
//                        )
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = Color.White,
//                    titleContentColor = Color.Black,
//                    navigationIconContentColor = Color.Black
//                )
//            )
//        },
//        modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
//    ) { paddingValues ->
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues)
//                .background(Color(0xFFF8F8F8))
//        ){
//            if(uiState.userProfile.firstName.isNotEmpty() && uiState.userProfile.address.isNotEmpty() &&
//                uiState.userProfile.pinCode.isNotEmpty() && uiState.userProfile.phoneNumber.isNotEmpty()
//            ){
//                Card(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .clickable {
////                        onClick()
//                        },
//                    shape = RoundedCornerShape(12.dp),
//                    colors = CardDefaults.cardColors(containerColor = Color.White),
//                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
//                ){
//                    Row(
//                        modifier = Modifier.fillMaxWidth()
//                    ) {
//                        // Name
//                        Text(
//                            text = uiState.userProfile.firstName +" "+ uiState.userProfile.lastName,
//                            fontSize = 12.sp,
//                            fontWeight = FontWeight.Medium,
//                            maxLines = 1,
//                            overflow = TextOverflow.Ellipsis
//                        )
//                        TextButton(
//                            onClick = {
////                            onChangeClick()
//                            }
//                        ) {
//                            Text(
//                                text = "Change",
//                                fontSize = 12.sp,
//                                fontWeight = FontWeight.Medium,
//                                maxLines = 1,
//                                overflow = TextOverflow.Ellipsis,
//                                color = Color(0xFFF83758)
//                            )
//                        }
//                    }
//                    Spacer(modifier = Modifier.height(2.dp))
//                    // Address
//                    Text(
//                        text = uiState.userProfile.address,
//                        fontSize = 11.sp,
////                    fontWeight = FontWeight.Medium,
//                        maxLines = 1,
//                        overflow = TextOverflow.Ellipsis
//                    )
//                    Spacer(modifier = Modifier.height(2.dp))
//                    Text(
//                        text = uiState.userProfile.city + ", " +uiState.userProfile.state + uiState.userProfile.pinCode,
//                        fontSize = 11.sp,
////                    fontWeight = FontWeight.Medium,
//                        maxLines = 1,
//                        overflow = TextOverflow.Ellipsis
//                    )
//                    Spacer(modifier = Modifier.height(6.dp))
//                    Text(
//                        text = buildAnnotatedString {
//                            append("Mobile:")
//                            withStyle(
//                                style = SpanStyle(
//                                    fontWeight = FontWeight.Medium
//                                )
//                            ){
//                                append(uiState.userProfile.phoneNumber)
//                            }
//                        },
//                        fontSize = 11.sp,
////                    fontWeight = FontWeight.Medium,
//                        maxLines = 1,
//                        overflow = TextOverflow.Ellipsis
//                    )
//                }
//            }
//            else{
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .clickable {
////                            onAddAddressClick()
//                        }
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Add,
//                        contentDescription = "Add address",
//                        modifier = Modifier.size(24.dp)
//                    )
//                    Spacer(modifier = Modifier.width(16.dp))
//
//                    Text(
//                        text = "Add Address",
//                        fontSize = 12.sp,
////                    fontWeight = FontWeight.Medium,
//                        maxLines = 1,
//                        overflow = TextOverflow.Ellipsis
//                    )
//                }
//            }
//        }
//    }
//}


package com.rksrtx76.nextbuy.presentation.cart

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.rksrtx76.nextbuy.domain.model.UserProfile
import com.rksrtx76.nextbuy.presentation.navigation.Routes
import com.rksrtx76.nextbuy.presentation.paymentSuccessfull.PaymentViewModel
import com.rksrtx76.nextbuy.presentation.userprofile.UserProfileViewModel
import com.rksrtx76.nextbuy.util.RazorpayHelper
import kotlin.math.roundToInt


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressScreen(
    navController: NavHostController,
    amount : Double,
    viewModel: UserProfileViewModel = hiltViewModel(),
    cartViewModel: CartViewModel,
    paymentViewModel: PaymentViewModel
) {
    val uiState by viewModel.state.collectAsState()
    val profile = uiState.userProfile
    val context = LocalContext.current
    val cartState by cartViewModel.state.collectAsState()
    val paymentState by paymentViewModel.state.collectAsState()

    val hasAddress = profile.firstName.isNotEmpty()
            && profile.address.isNotEmpty()
            && profile.pinCode.isNotEmpty()
            && profile.phoneNumber.isNotEmpty()

    LaunchedEffect(paymentState.isSuccess) {
        when(paymentState.isSuccess){
            true ->{
                // Clear cart
                cartViewModel.clearCart()
                // navigate to payment success screen
                navController.navigate(Routes.PaymentSuccessScreen(paymentId = paymentState.paymentId ?: "", amount = amount)){
                    popUpTo<Routes.AddressScreen>{
                        inclusive = true
                    }
                }
                // reset payment state
                paymentViewModel.resetPayment()
            }
            false ->{
                navController.navigate(Routes.PaymentFailedScreen(errorMessage = paymentState.errorMessage ?: "Payment failed", amount = amount)){
                    popUpTo<Routes.AddressScreen>{
                        inclusive = true
                    }
                }
                // reset payment state
                paymentViewModel.resetPayment()
            }

            null -> {
                // do nothing
            }
        }
    }

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
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
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
            if(hasAddress){
                AddressBottomBar(
                    onContinueClick = {
                        // Tell the ViewModel payment is starting
                        paymentViewModel.onPaymentStarted(amount)

                        RazorpayHelper.startPayment(
                            activity = context as Activity,
                            amount = amount
                        )
                    }
                )
            }
        },
        modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF8F8F8))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Text(
                    text = "DELIVERY ADDRESS",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                if (hasAddress) {
                    AddressCard(
                        profile = profile,
                        onClick = {
                            navController.navigate(Routes.ProfileScreen)
                        },
                         onChangeClick = {
                             navController.navigate(Routes.ProfileScreen)
                         }
                    )
                } else {
                    AddAddressCard(
                        onClick = { navController.navigate(Routes.ProfileScreen) }
                    )
                }
            }
        }
    }
}


@Composable
private fun AddressCard(
    profile: UserProfile,
    onClick: () -> Unit,
    onChangeClick : () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFF83758).copy(alpha = 0.10f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = Color(0xFFF83758),
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "${profile.firstName} ${profile.lastName}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Black
                    )
                }

                TextButton(onClick = { onChangeClick() }) {
                    Text(
                        text = "Change",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFFF83758)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = profile.address,
                fontSize = 12.sp,
                color = Color.DarkGray,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "${profile.city}, ${profile.state} – ${profile.pinCode}",
                fontSize = 12.sp,
                color = Color.DarkGray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = Color.Gray, fontSize = 11.sp)) {
                        append("Mobile  ")
                    }
                    withStyle(
                        SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            fontSize = 12.sp
                        )
                    ) {
                        append(profile.phoneNumber)
                    }
                },
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


@Composable
private fun AddAddressCard(onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF83758).copy(alpha = 0.10f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add address",
                    tint = Color(0xFFF83758),
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column {
                Text(
                    text = "Add Delivery Address",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Add an address for faster checkout",
                    fontSize = 11.sp,
                    color = Color.Gray,
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
fun AddressBottomBar(
    onContinueClick : () -> Unit
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
            Button(
                onClick = onContinueClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF83758)
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Continue",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
package com.rksrtx76.nextbuy.presentation.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.google.firebase.auth.FirebaseAuth
import com.rksrtx76.nextbuy.data.preference.UserPreferencesDataStore
import com.rksrtx76.nextbuy.data.repository.AuthRepositoryImpl
import com.rksrtx76.nextbuy.data.repository.UserPreferenceRepositoryImpl
import com.rksrtx76.nextbuy.domain.usecase.GetUserPreferenceUseCase
import com.rksrtx76.nextbuy.domain.usecase.SetUserPreferenceUseCase
import com.rksrtx76.nextbuy.domain.usecase.SignInUseCase
import com.rksrtx76.nextbuy.domain.usecase.SignUpUseCase
import com.rksrtx76.nextbuy.presentation.authentication.AuthViewModel
import com.rksrtx76.nextbuy.presentation.authentication.ForgotPasswordScreen
import com.rksrtx76.nextbuy.presentation.authentication.SignInScreen
import com.rksrtx76.nextbuy.presentation.authentication.SignUpScreen
import com.rksrtx76.nextbuy.presentation.cart.AddressScreen
import com.rksrtx76.nextbuy.presentation.cart.CartScreen
import com.rksrtx76.nextbuy.presentation.cart.CartViewModel
import com.rksrtx76.nextbuy.presentation.detailscreen.ProductDetailScreen
import com.rksrtx76.nextbuy.presentation.homescreen.HomeScreen
import com.rksrtx76.nextbuy.presentation.homescreen.ProductViewModel
import com.rksrtx76.nextbuy.presentation.onboarding.WelcomeScreen1
import com.rksrtx76.nextbuy.presentation.onboarding.WelcomeScreen2
import com.rksrtx76.nextbuy.presentation.onboarding.WelcomeScreen3
import com.rksrtx76.nextbuy.presentation.paymentSuccessfull.PaymentFailedScreen
import com.rksrtx76.nextbuy.presentation.paymentSuccessfull.PaymentSuccessScreen
import com.rksrtx76.nextbuy.presentation.paymentSuccessfull.PaymentViewModel
import com.rksrtx76.nextbuy.presentation.search.SearchScreen
import com.rksrtx76.nextbuy.presentation.splash.SplashScreen
import com.rksrtx76.nextbuy.presentation.userpreference.UserPreferencesViewModel
import com.rksrtx76.nextbuy.presentation.userprofile.UserProfileScreen
import com.rksrtx76.nextbuy.presentation.wishlist.WishlistScreen
import com.rksrtx76.nextbuy.presentation.wishlist.WishlistViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt


@Composable
fun AppNavigation(
    paymentViewModel: PaymentViewModel
) {
    val navController = rememberNavController()

    val authViewModel = hiltViewModel<AuthViewModel>()
    val userPreferenceViewModel = hiltViewModel<UserPreferencesViewModel>()
    val userPreferenceState by userPreferenceViewModel.state.collectAsState()
    val productViewModel = hiltViewModel<ProductViewModel>()
    val wishlistViewModel = hiltViewModel<WishlistViewModel>()
    val cartViewModel = hiltViewModel<CartViewModel>()

    Scaffold{ innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.SplashScreen
        ){
            composable<Routes.SignInScreen> {
                SignInScreen(
                    navController = navController,
                    authViewModel = authViewModel
                )
            }

            composable<Routes.SignUpScreen> {
                SignUpScreen(
                    navController = navController,
                    authViewModel = authViewModel
                )
            }

            composable<Routes.ForgotPasswordScreen> {
                ForgotPasswordScreen(
                    navController = navController
                )
            }

            composable<Routes.HomeScreen> {
                HomeScreen(
                    navController = navController,
                    productViewModel = productViewModel
                )
            }

            composable<Routes.ProductDetailScreen> { navBackstackEntry->
                val args = navBackstackEntry.toRoute<Routes.ProductDetailScreen>()
                ProductDetailScreen(
                    productId = args.productId,
                    navController = navController,
                    productViewModel = productViewModel,
                    wishlistViewModel = wishlistViewModel,
                    cartViewModel = cartViewModel
                )
            }

            composable<Routes.SearchScreen> {
                SearchScreen(
                    navController = navController,
                )
            }

            composable<Routes.WishlistScreen> {
                WishlistScreen(
                    navController = navController,
                    wishlistViewModel = wishlistViewModel
                )
            }

            composable<Routes.CartScreen> {
                CartScreen(
                    navController = navController,
                    cartViewModel = cartViewModel
                )
            }

            composable<Routes.ProfileScreen> {
                UserProfileScreen(
                    navController = navController,
                )
            }

            composable<Routes.WelcomeScreen1> {
                WelcomeScreen1(
                    navController = navController,
                    paddingValues = innerPadding
                )
            }

            composable<Routes.WelcomeScreen2> {
                WelcomeScreen2(
                    navController = navController,
                    paddingValues = innerPadding
                )
            }

            composable<Routes.WelcomeScreen3> {
                WelcomeScreen3(
                    navController = navController,
                    paddingValues = innerPadding
                )
            }


            composable<Routes.SplashScreen> {
                SplashScreen(
                    isLoading = userPreferenceState.isLoading,
                    onFinish = {
                        val destination = when{
                            userPreferenceState.firstTimeLogin -> Routes.WelcomeScreen1
                            userPreferenceState.isLoggedIn -> Routes.HomeScreen
                            else -> Routes.SignInScreen
                        }
                        navController.navigate(destination){
                            popUpTo<Routes.SplashScreen>{
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable<Routes.AddressScreen> { navBackstackEntry ->
                val args = navBackstackEntry.toRoute<Routes.AddressScreen>()
                AddressScreen(
                    navController = navController,
                    amount = args.amount,
                    cartViewModel = cartViewModel,
                    paymentViewModel = paymentViewModel
                )
            }

            composable<Routes.PaymentSuccessScreen> { navBackstackEntry ->
                val args = navBackstackEntry.toRoute<Routes.PaymentSuccessScreen>()
                val dateTime = remember {
                    SimpleDateFormat("dd MMM yy , hh:mm a", Locale.getDefault()).format(Date())
                }
                PaymentSuccessScreen(
                    amount = "₹${args.amount.roundToInt()}",
                    paymentId = args.paymentId,
                    dateTime = dateTime,
                    onDoneClick = {
                        navController.navigate(Routes.HomeScreen){
                            popUpTo<Routes.HomeScreen>{
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable<Routes.PaymentFailedScreen>(){ navBackstackEntry->
                val args = navBackstackEntry.toRoute<Routes.PaymentFailedScreen>()
                val dateTime = remember {
                    SimpleDateFormat("dd MMM yy , hh:mm a", Locale.getDefault()).format(Date())
                }
                PaymentFailedScreen(
                    amount = "₹${args.amount.roundToInt()}",
                    errorMessage = args.errorMessage,
                    dateTime = dateTime,
                    onRetryClick = {
                        navController.navigate(Routes.AddressScreen(amount = args.amount.roundToInt().toDouble())){
                            popUpTo<Routes.CartScreen>{
                                inclusive = true
                            }
                        }
                    },
                    onCancelClick = {
                        navController.navigate(Routes.HomeScreen){
                            popUpTo<Routes.HomeScreen> {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }
    }
}
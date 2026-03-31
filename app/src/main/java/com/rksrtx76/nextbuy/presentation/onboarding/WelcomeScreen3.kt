package com.rksrtx76.nextbuy.presentation.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rksrtx76.nextbuy.R
import com.rksrtx76.nextbuy.presentation.navigation.Routes
import com.rksrtx76.nextbuy.presentation.onboarding.common.components.BottomSection
import com.rksrtx76.nextbuy.presentation.onboarding.common.components.TopSection
import com.rksrtx76.nextbuy.presentation.onboarding.common.components.WelcomeScreenContent
import com.rksrtx76.nextbuy.presentation.userpreference.UserPreferencesViewModel


@Composable
fun WelcomeScreen3(
    navController : NavController,
    paddingValues: PaddingValues
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)
        .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        TopSection(
            currPage = 3,
            totalPage = 3,
            onSkip = {
                navController.navigate(Routes.SignInScreen){
                    popUpTo(Routes.WelcomeScreen1){
                        inclusive = true
                    }
                }
            },
            modifier = Modifier
        )

        WelcomeScreenContent(
            modifier = Modifier.weight(1f),
            topImage = R.drawable.image5,
            bottomImage = R.drawable.image4
        )

        BottomSection(
            modifier = Modifier,
            onPrev = { navController.popBackStack() },
            onNext = { navController.navigate(Routes.SignInScreen) },
            currentPage = 3
        )
    }
}



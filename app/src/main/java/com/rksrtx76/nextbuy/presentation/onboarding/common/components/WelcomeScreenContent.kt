package com.rksrtx76.nextbuy.presentation.onboarding.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun WelcomeScreenContent(
    modifier: Modifier = Modifier,
    topImage : Int,
    bottomImage : Int,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 36.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(topImage),
            contentDescription = null,
            contentScale = ContentScale.FillWidth

        )
        Image(
            painter = painterResource(bottomImage),
            contentDescription = null,
            contentScale = ContentScale.FillWidth

        )
    }
}
package com.rksrtx76.nextbuy.presentation.paymentSuccessfull

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Canvas
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.rksrtx76.nextbuy.ui.theme.FailedGlow
import com.rksrtx76.nextbuy.ui.theme.FailedRed
import com.rksrtx76.nextbuy.ui.theme.SuccessGlow
import com.rksrtx76.nextbuy.ui.theme.SuccessGreen
import java.util.Date
import kotlin.math.cos
import kotlin.math.sin


data class TransactionDetail(
    val label: String,
    val value: String
)

@Composable
fun AnimatedCheckIcon(
    color: Color,
    glowColor: Color,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.08f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    val ringAlpha by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1400, easing = EaseOutCirc),
            repeatMode = RepeatMode.Restart
        ),
        label = "ring"
    )

    val ringRadius by infiniteTransition.animateFloat(
        initialValue = 56f,
        targetValue = 80f,
        animationSpec = infiniteRepeatable(
            animation = tween(1400, easing = EaseOutCirc),
            repeatMode = RepeatMode.Restart
        ),
        label = "ringRadius"
    )

    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }

    val checkProgress by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(700, easing = EaseOutBack),
        label = "check"
    )

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.size(160.dp)) {
            drawCircle(
                color = glowColor.copy(alpha = ringAlpha),
                radius = ringRadius.dp.toPx(),
                style = Stroke(width = 2.dp.toPx())
            )

            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(glowColor, Color.Transparent),
                    radius = 70.dp.toPx()
                ),
                radius = 60.dp.toPx()
            )

            drawCircle(
                color = color,
                radius = 55.dp.toPx(),
                style = Stroke(width = 2.5.dp.toPx())
            )
        }

        Box(
            modifier = Modifier
                .size(112.dp)
                .scale(pulseScale)
                .clip(CircleShape)
                .background(color.copy(alpha = 0.18f)),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.size(48.dp)) {
                val p = checkProgress
                val stroke = Stroke(width = 5.dp.toPx(), cap = StrokeCap.Round)
                val path = Path().apply {
                    // Short arm of the tick
                    val x1 = size.width * 0.18f
                    val y1 = size.height * 0.52f
                    val x2 = size.width * 0.42f
                    val y2 = size.height * 0.76f
                    // Long arm of the tick
                    val x3 = size.width * 0.80f
                    val y3 = size.height * 0.26f

                    if (p <= 0.5f) {
                        val t = p / 0.5f
                        moveTo(x1, y1)
                        lineTo(lerp(x1, x2, t), lerp(y1, y2, t))
                    } else {
                        val t = (p - 0.5f) / 0.5f
                        moveTo(x1, y1)
                        lineTo(x2, y2)
                        lineTo(lerp(x2, x3, t), lerp(y2, y3, t))
                    }
                }
                drawPath(path, color = color, style = stroke)
            }
        }
    }
}

private fun lerp(a: Float, b: Float, t: Float) = a + (b - a) * t


@Composable
fun AnimatedXIcon(
    color: Color,
    glowColor: Color,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "shake")

    val rotation by infiniteTransition.animateFloat(
        initialValue = -2f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(900, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "rotation"
    )

    val ringAlpha by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1400, easing = EaseOutCirc),
            repeatMode = RepeatMode.Restart
        ),
        label = "ring"
    )

    val ringRadius by infiniteTransition.animateFloat(
        initialValue = 56f,
        targetValue = 80f,
        animationSpec = infiniteRepeatable(
            animation = tween(1400, easing = EaseOutCirc),
            repeatMode = RepeatMode.Restart
        ),
        label = "ringRadius"
    )

    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }

    val xProgress by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(600, easing = EaseOutBack),
        label = "xProg"
    )

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.size(160.dp)) {
            drawCircle(
                color = glowColor.copy(alpha = ringAlpha),
                radius = ringRadius.dp.toPx(),
                style = Stroke(width = 2.dp.toPx())
            )
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(glowColor, Color.Transparent),
                    radius = 70.dp.toPx()
                ),
                radius = 60.dp.toPx()
            )
            drawCircle(
                color = color,
                radius = 55.dp.toPx(),
                style = Stroke(width = 2.5.dp.toPx())
            )
        }

        Box(
            modifier = Modifier
                .size(112.dp)
                .clip(CircleShape)
                .background(color.copy(alpha = 0.18f)),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.size(44.dp)) {
                val stroke = Stroke(width = 5.dp.toPx(), cap = StrokeCap.Round)
                val p = xProgress
                val pad = size.width * 0.18f

                val path1 = Path().apply {
                    moveTo(pad, pad)
                    lineTo(lerp(pad, size.width - pad, p), lerp(pad, size.height - pad, p))
                }
                drawPath(path1, color = color, style = stroke)

                if (p > 0.5f) {
                    val t = (p - 0.5f) / 0.5f
                    val path2 = Path().apply {
                        moveTo(size.width - pad, pad)
                        lineTo(lerp(size.width - pad, pad, t), lerp(pad, size.height - pad, t))
                    }
                    drawPath(path2, color = color, style = stroke)
                }
            }
        }
    }
}

@Composable
fun DetailRow(detail: TransactionDetail, accentColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = detail.label,
            color = Color.Gray,
            fontSize = 13.sp,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = detail.value,
            color = if (detail.label == "Status") accentColor else Color.Black,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun BasePaymentScreen(
    isSuccess: Boolean,
    accentColor: Color,
    glowColor: Color,
    title: String,
    subtitle: String,
    amount: String,
    details: List<TransactionDetail>,
    primaryButtonText: String,
    primaryButtonColor: Color = Color(0xFFF83758),
    secondaryButtonText: String,
    onPrimaryClick: () -> Unit = {},
    onSecondaryClick: () -> Unit = {}
) {
    var contentVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { contentVisible = true }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9))
    ) {
        // Subtle glow at top
        Box(
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.TopCenter)
                .offset(y = (-80).dp)
                .background(
                    Brush.radialGradient(
                        colors = listOf(accentColor.copy(alpha = 0.08f), Color.Transparent)
                    ),
                    shape = CircleShape
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            if (isSuccess) {
                AnimatedCheckIcon(color = SuccessGreen, glowColor = SuccessGlow)
            } else {
                AnimatedXIcon(color = FailedRed, glowColor = FailedGlow)
            }

            Spacer(Modifier.height(28.dp))

            // ── Amount
            AnimatedVisibility(
                visible = contentVisible,
                enter = fadeIn(tween(600, delayMillis = 200)) + slideInVertically(
                    tween(500, delayMillis = 200)
                ) { it / 3 }
            ) {
                Text(
                    text = amount,
                    color = accentColor,
                    fontSize = 42.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = (-1).sp
                )
            }

            Spacer(Modifier.height(8.dp))

            AnimatedVisibility(
                visible = contentVisible,
                enter = fadeIn(tween(600, delayMillis = 300)) + slideInVertically(
                    tween(500, delayMillis = 300)
                ) { it / 3 }
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = title,
                        color = Color.Black,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = subtitle,
                        color = Color.Gray,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 20.sp
                    )
                }
            }

            Spacer(Modifier.height(32.dp))

            // Details card
            AnimatedVisibility(
                visible = contentVisible,
                enter = fadeIn(tween(600, delayMillis = 450)) + slideInVertically(
                    tween(600, delayMillis = 450)
                ) { it / 3 }
            ) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    color = Color.White,
                    tonalElevation = 0.dp,
                    shadowElevation = 2.dp
                ) {
                    Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)) {
                        details.forEach { DetailRow(it, accentColor) }
                    }
                }
            }

            Spacer(Modifier.height(32.dp))

            // Primary button
            AnimatedVisibility(
                visible = contentVisible,
                enter = fadeIn(tween(600, delayMillis = 600)) + slideInVertically(
                    tween(600, delayMillis = 600)
                ) { it / 2 }
            ) {
                Button(
                    onClick = onPrimaryClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = primaryButtonColor)
                ) {
                    Text(
                        text = primaryButtonText,
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            // Secondary button
            if(secondaryButtonText.isNotEmpty()){
                AnimatedVisibility(
                    visible = contentVisible,
                    enter = fadeIn(tween(600, delayMillis = 700))
                ) {
                    OutlinedButton(
                        onClick = onSecondaryClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray),
                        border = ButtonDefaults.outlinedButtonBorder.copy(
                            brush = Brush.linearGradient(listOf(Color.LightGray, Color.LightGray))
                        )
                    ) {
                        Text(
                            text = secondaryButtonText,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun PaymentSuccessScreen(
    amount: String,
    paymentId : String,
    dateTime : String,
    onDoneClick: () -> Unit = {},
) {
    val details = listOf(
        TransactionDetail("Transaction ID", "#${paymentId}"),
        TransactionDetail("Date & Time",    "${dateTime}"),
        TransactionDetail("Merchant",       "Razorpay"),
        TransactionDetail("Status",         "Successful"),
    )

    BasePaymentScreen(
        isSuccess          = true,
        accentColor        = SuccessGreen,
        glowColor          = SuccessGlow,
        title              = "Payment Successful",
        subtitle           = "Your transaction has been\nprocessed successfully.",
        amount             = amount,
        details            = details,
        primaryButtonText  = "Done",
        primaryButtonColor = Color(0xFFF83758),
        onPrimaryClick     = onDoneClick,
        onSecondaryClick = {},
        secondaryButtonText = ""
    )
}

@Composable
fun PaymentFailedScreen(
    amount: String,
    dateTime : String,
    errorMessage : String,
    onRetryClick: () -> Unit = {},
    onCancelClick: () -> Unit = {}
) {
    val details = listOf(
        TransactionDetail("Date & Time",    "${dateTime}"),
        TransactionDetail("Merchant",       "Razorpay"),
        TransactionDetail("Status",         "Failed"),
    )

    BasePaymentScreen(
        isSuccess           = false,
        accentColor         = FailedRed,
        glowColor           = FailedGlow,
        title               = "Payment Failed",
        subtitle            = errorMessage,
        amount              = amount,
        details             = details,
        primaryButtonText   = "Retry Payment",
        primaryButtonColor  = Color(0xFFF83758),
        secondaryButtonText = "Cancel",
        onPrimaryClick      = onRetryClick,
        onSecondaryClick    = onCancelClick
    )
}
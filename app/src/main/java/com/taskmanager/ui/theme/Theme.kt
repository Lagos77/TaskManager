package com.taskmanager.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.taskmanager.ui.Spacing

private val darkColorScheme = darkColorScheme(
    background = Black,
    onBackground = Tone20,
    primary = Tone70,
    onPrimary = Tone10,
    secondary = Tone60,
    onSecondary = Tone10,
    surface = Tone90,
    onSurface = Tone10,
    error = ErrorDark,
    onError = Color.Black,
    tertiary = Tone50,
    onTertiary = Tone10,
    surfaceVariant = Tone30,
    onSurfaceVariant = Tone90,
)

private val lightColorScheme = lightColorScheme(
    background = White,
    onBackground = Black,
    primary = Tone40,
    onPrimary = White,
    secondary = Tone30,
    onSecondary = White,
    surface = Tone10,
    onSurface = Tone90,
    error = ErrorLight,
    onError = White,
    tertiary = Tone50,
    onTertiary = White,
    surfaceVariant = Tone80,
    onSurfaceVariant = Tone10,
)

private val typography = androidx.compose.material3.Typography(
    titleLarge = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 32.sp,
    ),
    titleMedium = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 28.sp,
    ),
    bodyMedium = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
    ),
    labelLarge = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 20.sp,
    ),
    labelMedium = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 16.sp,
    ),
    labelSmall = TextStyle(
        fontSize = 10.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 14.sp,
    )
)

private val shape = Shapes(
    extraSmall = RoundedCornerShape(Spacing.extraSmall),
    small = RoundedCornerShape(Spacing.small),
    medium = RoundedCornerShape(Spacing.medium),
    large = RoundedCornerShape(Spacing.large),
    extraLarge = RoundedCornerShape(Spacing.extraLarge),
)

@Composable
fun TaskManagerTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (isDarkTheme) darkColorScheme else lightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        shapes = shape,
        typography = typography,
        content = content
    )
}

val Typography.titleLarge: TextStyle
    @Composable get() = typography.titleLarge

val Typography.titleNormal: TextStyle
    @Composable get() = typography.titleMedium

val Typography.body: TextStyle
    @Composable get() = typography.bodyMedium

val Typography.labelLarge: TextStyle
    @Composable get() = typography.labelLarge

val Typography.labelMedium: TextStyle
    @Composable get() = typography.labelMedium

val Typography.labelSmall: TextStyle
    @Composable get() = typography.labelSmall






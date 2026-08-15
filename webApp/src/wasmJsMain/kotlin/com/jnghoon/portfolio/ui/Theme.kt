package com.jnghoon.portfolio.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

fun Modifier.topBorder(color: Color, strokeWidth: Float = 1f): Modifier = drawBehind {
    drawLine(color = color, start = Offset(0f, 0f), end = Offset(size.width, 0f), strokeWidth = strokeWidth)
}

fun Modifier.bottomBorder(color: Color, strokeWidth: Float = 1f): Modifier = drawBehind {
    drawLine(color = color, start = Offset(0f, size.height), end = Offset(size.width, size.height), strokeWidth = strokeWidth)
}

fun Modifier.sideBorder(color: Color, strokeWidth: Float = 2f): Modifier = drawBehind {
    drawLine(color = color, start = Offset(0f, 0f), end = Offset(0f, size.height), strokeWidth = strokeWidth)
}

/** GitHub 프로필 스타일의 미니멀 라이트 색상 팔레트 */
object AppColors {
    val bg = Color(0xFFFFFFFF)
    val text = Color(0xFF24292F)
    val textSecondary = Color(0xFF57606A)
    val textTertiary = Color(0xFF8B949E)
    val accent = Color(0xFF0969DA)
    val border = Color(0xFFE8EAED)
    val surface = Color(0xFFF6F8FA)
    val tagBg = Color(0xFFEFF4F9)
    val tagText = Color(0xFF3B5568)
    val white = Color(0xFFFFFFFF)
}

/** Pretendard 폰트 로딩 전에는 null(플랫폼 기본 폰트) */
val LocalAppFontFamily = compositionLocalOf<FontFamily?> { null }

object AppType {
    private val family: FontFamily?
        @Composable get() = LocalAppFontFamily.current

    val navName: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = AppColors.text)
    val navLink: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 14.sp, color = AppColors.textSecondary)

    val heroName: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 27.sp, fontWeight = FontWeight.ExtraBold, letterSpacing = (-0.3).sp, color = AppColors.text)
    val heroEnglishName: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 15.sp, color = AppColors.textTertiary)
    val heroTitle: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = AppColors.accent)
    val heroSummary: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 14.sp, color = AppColors.textSecondary, lineHeight = 21.sp)
    val heroLink: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 13.sp, color = AppColors.textSecondary)
    val highlight: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 14.sp, color = AppColors.text)

    val sectionHeading: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 19.sp, fontWeight = FontWeight.Bold, letterSpacing = (-0.2).sp, color = AppColors.text)
    val body: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 14.sp, color = AppColors.text, lineHeight = 23.sp)

    val valueTitle: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = AppColors.text)
    val valueDesc: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 13.5.sp, color = AppColors.textSecondary, lineHeight = 20.sp)

    val skillName: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 13.5.sp, fontWeight = FontWeight.SemiBold, color = AppColors.text)

    val expCompany: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 16.5.sp, fontWeight = FontWeight.Bold, color = AppColors.text)
    val expPeriod: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 13.sp, color = AppColors.textTertiary)
    val expPosition: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 13.5.sp, color = AppColors.textSecondary)
    val timelineTitle: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = AppColors.text)
    val timelinePeriod: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 12.5.sp, color = AppColors.textTertiary)
    val timelineDesc: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 13.5.sp, color = AppColors.textSecondary, lineHeight = 20.sp)
    val timelineAchievement: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 13.5.sp, color = AppColors.text)
    val timelineAchievementStrong: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 13.5.sp, fontWeight = FontWeight.SemiBold, color = AppColors.accent)
    val tag: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 11.5.sp, fontWeight = FontWeight.Medium, color = AppColors.tagText)

    val projectTitle: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 18.5.sp, fontWeight = FontWeight.ExtraBold, letterSpacing = (-0.2).sp, color = AppColors.text)
    val projectKorean: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 14.sp, color = AppColors.textTertiary)
    val platformBadge: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 11.5.sp, fontWeight = FontWeight.SemiBold, color = AppColors.white)
    val projectSummary: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 14.5.sp, fontWeight = FontWeight.SemiBold, color = AppColors.text)
    val projectDesc: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 13.5.sp, color = AppColors.textSecondary, lineHeight = 20.sp)
    val metaLabel: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 13.5.sp, fontWeight = FontWeight.SemiBold, color = AppColors.textTertiary)
    val metaValue: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 13.5.sp, color = AppColors.textSecondary)
    val projectSub: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 13.5.sp, fontWeight = FontWeight.Bold, color = AppColors.text)
    val listItemBullet: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 13.5.sp, color = AppColors.textSecondary)

    val listItemTitle: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 14.sp, color = AppColors.text)
    val listItemSub: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 13.sp, color = AppColors.textSecondary)
    val listItemMeta: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 12.5.sp, color = AppColors.textTertiary)
    val badge: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 11.5.sp, fontWeight = FontWeight.SemiBold, color = AppColors.tagText)

    val footer: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 12.5.sp, color = AppColors.textTertiary)
    val statusText: TextStyle @Composable get() = TextStyle(fontFamily = family, fontSize = 14.sp, color = AppColors.textSecondary)
}

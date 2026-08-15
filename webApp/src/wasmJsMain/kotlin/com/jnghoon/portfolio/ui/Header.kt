package com.jnghoon.portfolio.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp

private val NAV_LINKS = listOf(
    "about" to "소개",
    "skills" to "기술",
    "experience" to "경력",
    "projects" to "프로젝트",
    "patents" to "특허·논문",
    "education" to "학력",
)

@Composable
fun Header(
    name: String,
    onLogoClick: () -> Unit,
    onNavClick: (String) -> Unit,
    onHeightMeasured: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .onSizeChanged { onHeightMeasured(it.height) }
            .background(AppColors.bg)
            .bottomBorder(AppColors.border)
            .padding(horizontal = 24.dp)
            .height(56.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BasicText(
            text = name,
            modifier = Modifier.clickable(onClick = onLogoClick),
            style = AppType.navName,
        )
        Row(horizontalArrangement = Arrangement.spacedBy(18.dp), verticalAlignment = Alignment.CenterVertically) {
            NAV_LINKS.forEach { (key, label) ->
                BasicText(
                    text = label,
                    modifier = Modifier.clickable { onNavClick(key) },
                    style = AppType.navLink,
                )
            }
        }
    }
}

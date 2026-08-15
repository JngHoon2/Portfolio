package com.jnghoon.portfolio.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.jnghoon.portfolio.model.Profile
import org.jetbrains.compose.resources.painterResource
import portfolio.generated.resources.Res
import portfolio.generated.resources.profile

@Composable
private fun ProfilePhoto(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(Res.drawable.profile),
        contentDescription = null,
        modifier = modifier.clip(CircleShape).border(1.dp, AppColors.border, CircleShape),
        contentScale = ContentScale.Crop,
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Hero(profile: Profile, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth().padding(top = 44.dp, bottom = 16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            ProfilePhoto(modifier = Modifier.size(112.dp))
            Spacer(Modifier.width(28.dp))
            Column {
                Row(verticalAlignment = Alignment.Bottom) {
                    BasicText(profile.name, style = AppType.heroName)
                    Spacer(Modifier.width(8.dp))
                    BasicText(profile.englishName, style = AppType.heroEnglishName)
                }
                BasicText(profile.title, style = AppType.heroTitle, modifier = Modifier.padding(top = 2.dp))
                BasicText(profile.summary, style = AppType.heroSummary, modifier = Modifier.padding(top = 10.dp))
                FlowRow(
                    modifier = Modifier.padding(top = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    HeroLink(
                        onClick = { openUrl("mailto:${profile.email}") },
                        icon = { MailIcon(AppColors.textSecondary) },
                        label = profile.email,
                    )
                    HeroLink(
                        onClick = { openUrl(profile.github) },
                        icon = { GithubIcon(AppColors.textSecondary) },
                        label = "GitHub",
                    )
                    HeroLink(
                        onClick = { openUrl(profile.archive) },
                        icon = { LinkIcon(AppColors.textSecondary) },
                        label = "Archive",
                    )
                    HeroLink(onClick = null, icon = { PhoneIcon(AppColors.textSecondary) }, label = profile.phone)
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .topBorder(AppColors.border),
        ) {
            profile.highlights.forEach { highlight ->
                Row(modifier = Modifier.padding(top = 12.dp)) {
                    BasicText("•  ", style = AppType.highlight.copy(color = AppColors.accent))
                    BasicText(highlight, style = AppType.highlight)
                }
            }
        }
    }
}

@Composable
private fun HeroLink(onClick: (() -> Unit)?, icon: @Composable () -> Unit, label: String) {
    Row(
        modifier = if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon()
        Spacer(Modifier.width(5.dp))
        BasicText(label, style = AppType.heroLink)
    }
}

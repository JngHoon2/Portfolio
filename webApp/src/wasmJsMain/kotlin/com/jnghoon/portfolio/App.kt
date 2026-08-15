package com.jnghoon.portfolio

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jnghoon.portfolio.data.PortfolioData
import com.jnghoon.portfolio.model.Portfolio
import com.jnghoon.portfolio.ui.AboutSection
import com.jnghoon.portfolio.ui.AppColors
import com.jnghoon.portfolio.ui.AppType
import com.jnghoon.portfolio.ui.EducationSection
import com.jnghoon.portfolio.ui.ExperienceSection
import com.jnghoon.portfolio.ui.Header
import com.jnghoon.portfolio.ui.Hero
import com.jnghoon.portfolio.ui.LocalAppFontFamily
import com.jnghoon.portfolio.ui.PatentsSection
import com.jnghoon.portfolio.ui.ProjectsSection
import com.jnghoon.portfolio.ui.PublicationsSection
import com.jnghoon.portfolio.ui.SkillsSection
import com.jnghoon.portfolio.ui.topBorder
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.Font
import portfolio.generated.resources.Res
import portfolio.generated.resources.pretendard_bold
import portfolio.generated.resources.pretendard_extrabold
import portfolio.generated.resources.pretendard_medium
import portfolio.generated.resources.pretendard_regular
import portfolio.generated.resources.pretendard_semibold
import kotlin.math.roundToInt

@Composable
internal fun rememberPretendardFontFamily(): FontFamily {
    val regular = Font(Res.font.pretendard_regular, FontWeight.Normal)
    val medium = Font(Res.font.pretendard_medium, FontWeight.Medium)
    val semibold = Font(Res.font.pretendard_semibold, FontWeight.SemiBold)
    val bold = Font(Res.font.pretendard_bold, FontWeight.Bold)
    val extrabold = Font(Res.font.pretendard_extrabold, FontWeight.ExtraBold)
    return remember(regular, medium, semibold, bold, extrabold) {
        FontFamily(regular, medium, semibold, bold, extrabold)
    }
}

@Composable
fun App() {
    CompositionLocalProvider(LocalAppFontFamily provides rememberPretendardFontFamily()) {
        PortfolioContent(PortfolioData)
    }
}

@Composable
internal fun PortfolioContent(portfolio: Portfolio) {
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val sectionOffsets = remember { mutableStateMapOf<String, Int>() }
    var headerHeight by remember { mutableStateOf(0) }

    fun scrollTo(offset: Int) {
        scope.launch { scrollState.animateScrollTo(offset.coerceAtLeast(0)) }
    }

    fun scrollToSection(key: String) {
        sectionOffsets[key]?.let { scrollTo(it - headerHeight) }
    }

    Column(modifier = Modifier.fillMaxSize().background(AppColors.bg)) {
        Header(
            name = portfolio.profile.name,
            onLogoClick = { scrollTo(0) },
            onNavClick = { key -> scrollToSection(key) },
            onHeightMeasured = { headerHeight = it },
        )
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ContentColumn {
                Hero(portfolio.profile)
                Anchored("about", scrollState, sectionOffsets) { AboutSection(portfolio.about) }
                Anchored("skills", scrollState, sectionOffsets) { SkillsSection(portfolio.skills) }
                Anchored("experience", scrollState, sectionOffsets) { ExperienceSection(portfolio.experiences) }
                Anchored("projects", scrollState, sectionOffsets) { ProjectsSection(portfolio.featuredProjects) }
                Anchored("patents", scrollState, sectionOffsets) { PatentsSection(portfolio.patents) }
                PublicationsSection(portfolio.publications)
                Anchored("education", scrollState, sectionOffsets) { EducationSection(portfolio.education) }
                Footer(portfolio)
            }
        }
    }
}

@Composable
private fun ContentColumn(content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier.widthIn(max = 720.dp).fillMaxWidth().padding(horizontal = 24.dp),
        content = content,
    )
}

@Composable
private fun Anchored(
    key: String,
    scrollState: ScrollState,
    offsets: MutableMap<String, Int>,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier.onGloballyPositioned { coordinates ->
            offsets[key] = coordinates.positionInRoot().y.roundToInt() + scrollState.value
        },
    ) {
        content()
    }
}

@Composable
private fun Footer(portfolio: Portfolio) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 56.dp)
            .topBorder(AppColors.border)
            .padding(top = 28.dp, bottom = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BasicText(
            text = "© ${portfolio.currentYear} ${portfolio.profile.name} (${portfolio.profile.englishName}) · " +
                "Built with Compose Multiplatform",
            style = AppType.footer,
        )
    }
}

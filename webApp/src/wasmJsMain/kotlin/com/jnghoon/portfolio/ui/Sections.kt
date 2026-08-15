package com.jnghoon.portfolio.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.jnghoon.portfolio.model.About
import com.jnghoon.portfolio.model.EducationItem
import com.jnghoon.portfolio.model.Experience
import com.jnghoon.portfolio.model.FeaturedProject
import com.jnghoon.portfolio.model.Patent
import com.jnghoon.portfolio.model.Publication
import com.jnghoon.portfolio.model.Skill

@Composable
private fun Section(title: String, modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Column(modifier = modifier.fillMaxWidth().padding(top = 36.dp)) {
        BasicText(
            text = title,
            style = AppType.sectionHeading,
            modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp).bottomBorder(AppColors.border),
        )
        Spacer(Modifier.height(22.dp))
        content()
    }
}

@Composable
private fun Tag(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(AppColors.tagBg)
            .padding(horizontal = 10.dp, vertical = 3.dp),
    ) {
        BasicText(text, style = AppType.tag)
    }
}

@Composable
private fun ListRow(title: String, sub: String?, meta: String, badge: String? = null, showDivider: Boolean = true) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 11.dp)
            .let { if (showDivider) it.bottomBorder(AppColors.border) else it },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (badge != null) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(AppColors.tagBg)
                        .padding(horizontal = 6.dp, vertical = 1.dp),
                ) {
                    BasicText(badge, style = AppType.badge)
                }
                Spacer(Modifier.width(8.dp))
            }
            BasicText(title, style = AppType.listItemTitle)
            if (sub != null) {
                Spacer(Modifier.width(8.dp))
                BasicText(sub, style = AppType.listItemSub)
            }
        }
        BasicText(meta, style = AppType.listItemMeta)
    }
}

@Composable
fun AboutSection(about: About, modifier: Modifier = Modifier) {
    Section(title = "소개", modifier = modifier) {
        Column {
            about.paragraphs.forEach { paragraph ->
                BasicText(paragraph, style = AppType.body, modifier = Modifier.padding(bottom = 14.dp))
            }
            Spacer(Modifier.height(14.dp))
            about.values.forEach { value ->
                Column(modifier = Modifier.padding(bottom = 18.dp)) {
                    BasicText(value.title, style = AppType.valueTitle, modifier = Modifier.padding(bottom = 4.dp))
                    BasicText(value.description, style = AppType.valueDesc)
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SkillsSection(skills: List<Skill>, modifier: Modifier = Modifier) {
    Section(title = "주요 기술", modifier = modifier) {
        FlowRow(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            skills.forEach { skill ->
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(AppColors.surface)
                        .padding(horizontal = 12.dp, vertical = 7.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    BasicText(skill.name, style = AppType.skillName)
                    Spacer(Modifier.width(8.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                        (1..3).forEach { n ->
                            Box(
                                modifier = Modifier
                                    .size(7.dp)
                                    .clip(CircleShape)
                                    .background(if (n <= skill.levelValue) AppColors.accent else AppColors.border),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ExperienceSection(experiences: List<Experience>, modifier: Modifier = Modifier) {
    Section(title = "경력", modifier = modifier) {
        Column {
            experiences.forEach { exp ->
                Column(modifier = Modifier.padding(bottom = 28.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom,
                    ) {
                        BasicText(exp.company, style = AppType.expCompany)
                        BasicText("${exp.period} · ${exp.duration}", style = AppType.expPeriod)
                    }
                    BasicText(exp.position, style = AppType.expPosition, modifier = Modifier.padding(bottom = 22.dp, top = 2.dp))
                    Column(
                        modifier = Modifier.padding(start = 6.dp).sideBorder(AppColors.border).padding(start = 22.dp),
                        verticalArrangement = Arrangement.spacedBy(26.dp),
                    ) {
                        exp.subProjects.forEach { sp ->
                            Column {
                                Row(verticalAlignment = Alignment.Bottom) {
                                    BasicText(sp.title, style = AppType.timelineTitle)
                                    Spacer(Modifier.width(8.dp))
                                    BasicText(sp.period, style = AppType.timelinePeriod)
                                }
                                BasicText(sp.description, style = AppType.timelineDesc, modifier = Modifier.padding(top = 4.dp, bottom = 8.dp))
                                Row(modifier = Modifier.padding(bottom = 8.dp)) {
                                    BasicText("성과 · ", style = AppType.timelineAchievement)
                                    BasicText(sp.achievement, style = AppType.timelineAchievementStrong)
                                }
                                FlowTags(sp.techStack)
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FlowTags(tags: List<String>) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
        tags.forEach { Tag(it) }
    }
}

@Composable
fun ProjectsSection(projects: List<FeaturedProject>, modifier: Modifier = Modifier) {
    Section(title = "대표 프로젝트", modifier = modifier) {
        Column {
            projects.forEach { project ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(AppColors.bg)
                        .border(1.dp, AppColors.border, RoundedCornerShape(12.dp))
                        .padding(horizontal = 28.dp, vertical = 26.dp),
                ) {
                    Row(verticalAlignment = Alignment.Bottom, modifier = Modifier.fillMaxWidth()) {
                        BasicText(project.name, style = AppType.projectTitle)
                        Spacer(Modifier.width(10.dp))
                        BasicText(project.koreanName, style = AppType.projectKorean)
                        Spacer(Modifier.weight(1f))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(AppColors.accent)
                                .padding(horizontal = 9.dp, vertical = 2.dp),
                        ) {
                            BasicText(project.platform, style = AppType.platformBadge)
                        }
                    }
                    BasicText(project.summary, style = AppType.projectSummary, modifier = Modifier.padding(top = 8.dp, bottom = 10.dp))
                    project.description.forEach {
                        BasicText(it, style = AppType.projectDesc, modifier = Modifier.padding(bottom = 8.dp))
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(AppColors.surface)
                            .padding(horizontal = 16.dp, vertical = 14.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                    ) {
                        MetaRow("기간", project.period)
                        MetaRow("역할", "${project.roles.joinToString(", ")} (총 ${project.teamSize}명 중)")
                        project.techStack.forEach { (k, v) -> MetaRow(k, v) }
                    }
                    BasicText("수행 작업", style = AppType.projectSub, modifier = Modifier.padding(top = 6.dp, bottom = 8.dp))
                    project.tasks.forEach { BulletLine(it) }
                    BasicText("회고", style = AppType.projectSub, modifier = Modifier.padding(top = 14.dp, bottom = 8.dp))
                    project.retrospective.forEach { BulletLine(it) }
                }
            }
        }
    }
}

@Composable
private fun MetaRow(label: String, value: String) {
    Row {
        BasicText(label, style = AppType.metaLabel, modifier = Modifier.width(92.dp))
        BasicText(value, style = AppType.metaValue)
    }
}

@Composable
private fun BulletLine(text: String) {
    Row(modifier = Modifier.padding(bottom = 5.dp)) {
        BasicText("·  ", style = AppType.listItemBullet.copy(color = AppColors.accent))
        BasicText(text, style = AppType.listItemBullet)
    }
}

@Composable
fun PatentsSection(patents: List<Patent>, modifier: Modifier = Modifier) {
    Section(title = "특허", modifier = modifier) {
        Column {
            patents.forEachIndexed { index, patent ->
                ListRow(
                    title = patent.title,
                    sub = null,
                    meta = patent.applicationNumber,
                    badge = patent.status,
                    showDivider = index != patents.lastIndex,
                )
            }
        }
    }
}

@Composable
fun PublicationsSection(publications: List<Publication>, modifier: Modifier = Modifier) {
    Section(title = "논문", modifier = modifier) {
        Column {
            publications.forEachIndexed { index, publication ->
                ListRow(
                    title = publication.title,
                    sub = publication.venue,
                    meta = publication.date,
                    showDivider = index != publications.lastIndex,
                )
            }
        }
    }
}

@Composable
fun EducationSection(education: List<EducationItem>, modifier: Modifier = Modifier) {
    Section(title = "학력", modifier = modifier) {
        Column {
            education.forEachIndexed { index, item ->
                ListRow(
                    title = item.school,
                    sub = item.major,
                    meta = item.period,
                    showDivider = index != education.lastIndex,
                )
            }
        }
    }
}

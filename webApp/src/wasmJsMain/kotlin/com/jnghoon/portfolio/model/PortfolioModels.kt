package com.jnghoon.portfolio.model

// 정적 SPA 이므로 백엔드/직렬화 없이 코드에 직접 데이터를 담는 순수 모델이다.

data class Profile(
    val name: String,
    val englishName: String,
    val title: String,
    val summary: String,
    val highlights: List<String>,
    val email: String,
    val phone: String,
    val github: String,
    val archive: String,
)

data class ValueItem(
    val title: String,
    val description: String,
)

data class About(
    val paragraphs: List<String>,
    val values: List<ValueItem>,
)

data class SubProject(
    val title: String,
    val period: String,
    val description: String,
    val techStack: List<String>,
    val achievement: String,
)

data class Experience(
    val company: String,
    val position: String,
    val period: String,
    val duration: String,
    val subProjects: List<SubProject>,
)

data class FeaturedProject(
    val name: String,
    val koreanName: String,
    val platform: String,
    val summary: String,
    val description: List<String>,
    val period: String,
    val teamSize: Int,
    val roles: List<String>,
    val techStack: Map<String, String>,
    val tasks: List<String>,
    val retrospective: List<String>,
)

data class Patent(
    val title: String,
    val status: String,
    val applicationNumber: String,
)

data class Publication(
    val title: String,
    val venue: String,
    val date: String,
)

data class EducationItem(
    val school: String,
    val major: String,
    val period: String,
)

data class Skill(
    val name: String,
    val level: String,
    val levelValue: Int,
)

data class Portfolio(
    val profile: Profile,
    val about: About,
    val experiences: List<Experience>,
    val featuredProjects: List<FeaturedProject>,
    val patents: List<Patent>,
    val publications: List<Publication>,
    val education: List<EducationItem>,
    val skills: List<Skill>,
    val currentYear: Int,
)

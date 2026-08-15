package com.jnghoon.portfolio.data

import com.jnghoon.portfolio.model.About
import com.jnghoon.portfolio.model.EducationItem
import com.jnghoon.portfolio.model.Experience
import com.jnghoon.portfolio.model.FeaturedProject
import com.jnghoon.portfolio.model.Patent
import com.jnghoon.portfolio.model.Portfolio
import com.jnghoon.portfolio.model.Profile
import com.jnghoon.portfolio.model.Publication
import com.jnghoon.portfolio.model.Skill
import com.jnghoon.portfolio.model.SubProject
import com.jnghoon.portfolio.model.ValueItem

/** 백엔드 없이 정적으로 렌더링하기 위한 포트폴리오 데이터. */
val PortfolioData = Portfolio(
    profile = Profile(
        name = "이정훈",
        englishName = "Lee Jeong Hoon",
        title = "Mobile Engineer · Android / iOS",
        summary = "헬스케어 스타트업에서 Android 와 iOS 플랫폼을 넘나들며 " +
            "기획·설계·개발·배포 전 과정을 경험한 모바일 엔지니어입니다.",
        highlights = listOf(
            "모듈 단위부터 플랫폼 서비스까지 기획·설계·개발·배포 전 과정 리딩",
            "Jetpack Compose · SwiftUI 기반 크로스 플랫폼 앱 설계 및 구현",
        ),
        email = "jhmh0226@gmail.com",
        phone = "010-9148-0342",
        github = "https://github.com/JngHoon2",
        archive = "https://tuan0324.netlify.app",
    ),
    about = About(
        paragraphs = listOf(
            "\"내가 개발한 서비스로 이로움을 주자\"라는 마음으로 헬스케어 스타트업에서 " +
                "Android 와 iOS 플랫폼을 넘나들며 모바일 엔지니어로 일해왔습니다. " +
                "모듈 단위부터 플랫폼 서비스까지 기획·설계·개발·배포 전 과정을 경험했고, " +
                "팀 내 테크리더로서 서비스 전체 흐름을 함께 고민하는 개발을 지향합니다.",
        ),
        values = listOf(
            ValueItem(
                title = "주인의식을 갖고 서비스를 이해합니다",
                description = "작은 부분까지 꼼꼼하게 살피며 사용자 경험과 서비스 흐름을 함께 고민합니다.",
            ),
            ValueItem(
                title = "일관된 구조를 설계합니다",
                description = "클린 아키텍처와 일관된 패턴으로 유지보수하기 좋은 코드를 만듭니다.",
            ),
        ),
    ),
    experiences = listOf(
        Experience(
            company = "아이다이나믹스",
            position = "개발팀 · 선임연구원 / 테크리더",
            period = "2023.03 ~ 2025.10",
            duration = "2년 8개월",
            subProjects = listOf(
                SubProject(
                    title = "EyeLink Android 앱 개발",
                    period = "2025.05 ~ 2025.10",
                    description = "모바일 안구 질환 검사 및 질의 응답형 수술 사후 관리 Android 앱 개발",
                    techStack = listOf("Kotlin", "Jetpack Compose", "Hilt", "ExoPlayer", "MediaPipe"),
                    achievement = "서비스 배포 · 디지털 의료기기 인허가 1등급 등록",
                ),
                SubProject(
                    title = "CiERA iOS 앱 개발",
                    period = "2024.12 ~ 2025.05",
                    description = "안구 질환 검사 및 챗봇형 사후 관리 iOS 앱 개발, 온디바이스 AI 엔진 포팅",
                    techStack = listOf("Swift", "SwiftUI", "C++", "Objective-C", "MediaPipe"),
                    achievement = "서비스 배포 · 온디바이스 AI 엔진(TensorFlow, ONNX) 포팅",
                ),
            ),
        ),
    ),
    featuredProjects = listOf(
        FeaturedProject(
            name = "EyeLink",
            koreanName = "아이링크",
            platform = "Android",
            summary = "안구 질환 검사 및 질의 응답형 사후 관리 Android 앱",
            description = listOf(
                "사용자는 시력 검사·대비 감도 검사를 통해 안구 관리 상태를 확인하고, " +
                    "수술 후 질의 응답형 사후 관리를 받을 수 있습니다.",
            ),
            period = "2025.03 ~ 2025.11",
            teamSize = 5,
            roles = listOf("Project Leader (100%)", "Android Developer (70%)"),
            techStack = mapOf(
                "Front-End" to "Kotlin, Jetpack, Hilt, ExoPlayer, FCM, MediaPipe",
                "Back-End" to "Java, Spring, MySQL",
            ),
            tasks = listOf(
                "프로젝트 일정 관리 및 개발 범위 의사 결정",
                "시스템 아키텍처 및 데이터베이스 설계",
                "디지털 의료기기 인허가 1등급 등록 (UDI-DI: 8800344780000)",
            ),
            retrospective = listOf(
                "클린 아키텍처·MVVM 패턴과 멀티 모듈을 구현하며 관심사 분리의 중요성을 알게 되었습니다.",
            ),
        ),
        FeaturedProject(
            name = "CiERA",
            koreanName = "씨에라",
            platform = "iOS",
            summary = "안구 질환 검사 및 챗봇형 사후 관리 iOS 앱",
            description = listOf(
                "온디바이스 AI 엔진을 포팅해 네트워크 없이도 안구 이미지를 분석하고, " +
                    "챗봇 형태로 사용자 사후 관리를 제공합니다.",
            ),
            period = "2024.12 ~ 2025.05",
            teamSize = 4,
            roles = listOf("Project Manager (100%)", "iOS Developer (80%)"),
            techStack = mapOf(
                "Front-End" to "Swift, SwiftUI, C++, Objective-C, Alamofire, FCM, MediaPipe",
                "AI Engine" to "TensorFlow, ONNX",
            ),
            tasks = listOf(
                "프로젝트 매니저 겸 iOS 개발자로 서비스 배포",
                "C++ 기반 온디바이스 AI 엔진 포팅 및 iOS 브릿징",
            ),
            retrospective = listOf(
                "네이티브와 C++ 엔진을 연결하며 플랫폼 경계를 넘나드는 통합 설계 역량을 키웠습니다.",
            ),
        ),
    ),
    patents = listOf(
        Patent("스마트폰 기반 근접 안구 촬영 시스템", "출원", "10-2024-0166269"),
        Patent("AI 기반 초해상도 안구 이미지 획득·동공 반응 분석 시스템 외 5건", "출원", "2024"),
    ),
    publications = listOf(
        Publication(
            title = "SmartPLR: A digital solution for AI-powered smartphone pupillometry",
            venue = "BMC",
            date = "2025.11",
        ),
    ),
    education = listOf(
        EducationItem("건양대학교", "의료IT공학과", "2017.03 ~ 2023.02"),
    ),
    skills = listOf(
        Skill("Kotlin", "상", 3),
        Skill("Jetpack Compose", "상", 3),
        Skill("Android", "상", 3),
        Skill("Swift", "중", 2),
        Skill("SwiftUI", "중", 2),
        Skill("iOS", "중", 2),
        Skill("C++", "중", 2),
        Skill("MediaPipe", "중", 2),
    ),
    currentYear = 2026,
)

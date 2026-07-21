// 플러그인 해석에 사용할 저장소 설정
pluginManagement {
    repositories {
        // Compose Multiplatform 플러그인 등 JetBrains 전용 아티팩트 저장소
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

// 라이브러리 의존성 해석에 사용할 저장소 설정
dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

// Gradle 루트 프로젝트 이름
rootProject.name = "portfolio"

// webApp 모듈 등록 (브라우저에서 실행되는 정적 SPA)
include(":webApp")

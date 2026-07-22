// webApp 모듈: Compose Multiplatform for Web (Kotlin/Wasm) 정적 SPA
plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    // 브라우저에서 실행되는 WebAssembly(wasmJs) 타겟
    @OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
    wasmJs {
        // 브라우저 환경으로 빌드 (Node.js 아님)
        browser {
            // 정적 산출물의 기준이 되는 index.html 등이 위치한 출력 모듈명
            commonWebpackConfig {
                outputFileName = "portfolio.js"
            }
        }
        // 실행 가능한 바이너리(=정적 SPA)로 빌드
        binaries.executable()
    }

    sourceSets {
        val wasmJsMain by getting {
            dependencies {
                // Compose 런타임 및 기본 UI 구성 요소
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                // 브라우저 렌더링을 위한 HTML 통합 (백엔드 없이 순수 UI)
                implementation(compose.ui)
                implementation(compose.components.resources)
            }
        }
    }
}

// Compose 리소스 접근자(Res) 클래스 생성 설정
// composeResources/font 아래의 한글 폰트(ttf)를 Res.font.* 로 참조하기 위함
compose.resources {
    publicResClass = true
    packageOfResClass = "portfolio.generated.resources"
    generateResClass = always
}

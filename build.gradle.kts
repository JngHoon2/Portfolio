// 루트 빌드 스크립트: 하위 모듈에서 사용할 플러그인의 버전만 선언하고
// 실제 적용(apply)은 각 모듈에서 하도록 apply false 로 관리한다.
plugins {
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.composeMultiplatform) apply false
}

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import org.jetbrains.compose.resources.Font
import portfolio.generated.resources.Res
import portfolio.generated.resources.pretendard_bold
import portfolio.generated.resources.pretendard_medium
import portfolio.generated.resources.pretendard_regular
import portfolio.generated.resources.pretendard_semibold

// wasmJs 진입점: 브라우저 body 에 Compose UI 를 마운트한다.
@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        PortfolioApp()
    }
}

// 전역 색상 팔레트 (미니멀 흑백 톤)
private val InkColor = Color(0xFF1A1A1A)      // 본문 텍스트
private val SubtleColor = Color(0xFF6B6B6B)   // 보조 텍스트
private val LinkColor = Color(0xFF2563EB)     // 링크 강조색
private val LineColor = Color(0xFFE5E5E5)     // 구분선

// 한글을 지원하는 Pretendard 폰트 패밀리 (weight 별 ttf 매핑)
@Composable
private fun pretendardFamily(): FontFamily = FontFamily(
    Font(Res.font.pretendard_regular, FontWeight.Normal),
    Font(Res.font.pretendard_medium, FontWeight.Medium),
    Font(Res.font.pretendard_semibold, FontWeight.SemiBold),
    Font(Res.font.pretendard_bold, FontWeight.Bold)
)

// 포트폴리오 루트 화면: 흰 배경 위 가운데 정렬된 단일 컬럼 레이아웃
@Composable
private fun PortfolioApp() {
    val fontFamily = pretendardFamily()
    val fontFamilyResolver = LocalFontFamilyResolver.current
    // 폰트 다운로드가 끝나기 전에 그리면 폴백 폰트로 렌더링되어 한글이 잠깐 엑박(□)으로 보인다.
    // 로드가 끝날 때까지 빈 화면으로 대기했다가 완료 후 본문을 그린다.
    var fontsLoaded by remember { mutableStateOf(false) }
    LaunchedEffect(fontFamily) {
        fontFamilyResolver.preload(fontFamily)
        fontsLoaded = true
    }
    if (!fontsLoaded) {
        Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {}
        return
    }

    // 앱 전역 기본 텍스트 스타일에 한글 폰트 적용 (모든 Text 가 상속)
    val baseStyle = LocalTextStyle.current.copy(fontFamily = fontFamily)
    MaterialTheme {
        CompositionLocalProvider(LocalTextStyle provides baseStyle) {
        Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
            // 화면 전체 세로 스크롤
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                contentAlignment = Alignment.TopCenter
            ) {
                // 본문 너비를 제한해 넓은 화면에서도 가독성 유지 (반응형)
                Column(
                    modifier = Modifier
                        .widthIn(max = 720.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 72.dp)
                ) {
                    Header()
                    Spacer(Modifier.height(28.dp))
                    Intro()
                    Spacer(Modifier.height(40.dp))
                    ExperienceSection()
                    Spacer(Modifier.height(40.dp))
                    ProjectSection()
                    Spacer(Modifier.height(40.dp))
                    AchievementSection()
                    Spacer(Modifier.height(40.dp))
                    EducationSection()
                    Spacer(Modifier.height(56.dp))
                    Footer()
                }
            }
        }
        }
    }
}

// 상단 헤더: 이름 / 직함 / 연락처 링크
@Composable
private fun Header() {
    Column {
        Text(
            text = "이정훈",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = InkColor
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "Lee Jeong Hoon",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = SubtleColor
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = "헬스케어 분야 모바일 엔지니어 · Android / iOS",
            fontSize = 16.sp,
            color = InkColor
        )
        Spacer(Modifier.height(16.dp))
        // 외부 링크 모음
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            LinkText("Email", "mailto:jhmh0226@gmail.com")
            LinkText("GitHub", "https://github.com/JngHoon2")
            LinkText("Archive", "https://tuan0324.netlify.app")
        }
    }
}

// 자기소개 문단
@Composable
private fun Intro() {
    Paragraph(
        "\"내가 개발한 서비스로 이로움을 주자\"라는 마음으로 헬스케어 스타트업에서 " +
            "Android 와 iOS 플랫폼을 넘나들며 모바일 엔지니어로 일해왔습니다. " +
            "모듈 단위부터 플랫폼 서비스까지 기획·설계·개발·배포 전 과정을 경험했고, " +
            "팀 내 테크리더로서 서비스 전체 흐름을 함께 고민하는 개발을 지향합니다."
    )
    Spacer(Modifier.height(12.dp))
    Paragraph(
        "작은 부분까지 꼼꼼하게 살피며 일관된 구조를 만드는 데 강점이 있으며, " +
            "사용자 경험과 서비스 흐름을 함께 고민하는 주인의식을 중요하게 생각합니다."
    )
}

// 경력 섹션
@Composable
private fun ExperienceSection() {
    Section(title = "Experience") {
        Entry(
            title = "아이다이나믹스 · 선임연구원 / 테크리더",
            period = "2023.03 – 2025.10 (2년 8개월)",
            bullets = listOf(
                "헬스케어 모바일 서비스의 기획·설계·개발·배포 전 과정 리딩",
                "Android(Kotlin, Jetpack Compose) 및 iOS(Swift, SwiftUI) 크로스 플랫폼 개발",
                "MediaPipe · OpenCV 기반 영상 처리 및 온디바이스 AI 엔진 포팅",
                "클린 아키텍처 · MVVM · 멀티 모듈 기반 앱 아키텍처 설계"
            )
        )
    }
}

// 대표 프로젝트 섹션
@Composable
private fun ProjectSection() {
    Section(title = "Projects") {
        Entry(
            title = "EyeLink · 안구 질환 검사 및 사후 관리 Android 앱",
            period = "2025.03 – 2025.11",
            bullets = listOf(
                "프로젝트 리더 겸 Android 개발자로 서비스 배포",
                "디지털 의료기기 인허가 1등급 등록 (UDI-DI: 8800344780000)",
                "Kotlin, Jetpack, Hilt, ExoPlayer, FCM, MediaPipe"
            )
        )
        Spacer(Modifier.height(20.dp))
        Entry(
            title = "CiERA · 안구 질환 검사 및 챗봇형 사후 관리 iOS 앱",
            period = "2024.12 – 2025.05",
            bullets = listOf(
                "프로젝트 매니저 겸 iOS 개발자로 서비스 배포",
                "C++ 기반 온디바이스 AI 엔진 포팅 (TensorFlow, ONNX)",
                "Swift, SwiftUI, C++, Objective-C, Alamofire, FCM, MediaPipe"
            )
        )
    }
}

// 특허 · 논문 섹션
@Composable
private fun AchievementSection() {
    Section(title = "Patents & Publications") {
        Entry(
            title = "특허 출원 7건",
            period = "2024",
            bullets = listOf(
                "스마트폰 기반 근접 안구 촬영 시스템 외 6건",
                "AI 기반 초해상도 안구 이미지 획득 · 동공 반응 분석 시스템 등"
            )
        )
        Spacer(Modifier.height(20.dp))
        Entry(
            title = "SmartPLR: A digital solution for AI-powered smartphone pupillometry",
            period = "2025.11",
            bullets = listOf("스마트폰 기반 AI 동공 측정 솔루션 논문")
        )
    }
}

// 학력 섹션
@Composable
private fun EducationSection() {
    Section(title = "Education") {
        Entry(
            title = "건양대학교 · 의료IT공학과",
            period = "2017.03 – 2023.02",
            bullets = emptyList()
        )
    }
}

// 하단 푸터
@Composable
private fun Footer() {
    Column {
        Divider(color = LineColor, thickness = 1.dp)
        Spacer(Modifier.height(16.dp))
        Text(
            text = "© 2026 Lee Jeong Hoon",
            fontSize = 13.sp,
            color = SubtleColor
        )
    }
}

// ---------- 재사용 컴포넌트 ----------

// 섹션 제목 + 내용 블록
@Composable
private fun Section(title: String, content: @Composable () -> Unit) {
    Column {
        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = SubtleColor,
            letterSpacing = 1.sp
        )
        Spacer(Modifier.height(16.dp))
        content()
    }
}

// 개별 항목: 제목 / 기간 / 세부 불릿
@Composable
private fun Entry(title: String, period: String, bullets: List<String>) {
    Column {
        Text(
            text = title,
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
            color = InkColor
        )
        Spacer(Modifier.height(2.dp))
        Text(text = period, fontSize = 14.sp, color = SubtleColor)
        if (bullets.isNotEmpty()) {
            Spacer(Modifier.height(8.dp))
            bullets.forEach { bullet ->
                Row(modifier = Modifier.padding(vertical = 2.dp)) {
                    Text(text = "· ", fontSize = 15.sp, color = SubtleColor)
                    Text(
                        text = bullet,
                        fontSize = 15.sp,
                        color = InkColor,
                        lineHeight = 22.sp
                    )
                }
            }
        }
    }
}

// 본문 문단 (줄 간격 넉넉하게)
@Composable
private fun Paragraph(text: String) {
    Text(
        text = text,
        fontSize = 15.sp,
        color = InkColor,
        lineHeight = 24.sp
    )
}

// 클릭 시 새 창/외부 URL 로 이동하는 링크 텍스트
@Composable
private fun LinkText(label: String, url: String) {
    val uriHandler = LocalUriHandler.current
    Text(
        text = label,
        fontSize = 15.sp,
        color = LinkColor,
        textDecoration = TextDecoration.Underline,
        modifier = Modifier.clickable { uriHandler.openUri(url) }
    )
}

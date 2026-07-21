# Portfolio

이정훈(Lee Jeong Hoon)의 개인 포트폴리오 정적 사이트입니다.

- **도메인**: [jeonghoonlee.com](https://jeonghoonlee.com)
- **프론트엔드**: Compose Multiplatform for Web (Kotlin/Wasm, wasmJs 타겟)
- **서빙 / HTTPS**: Caddy (Let's Encrypt 인증서 자동 발급·갱신)
- **배포**: Docker Compose (Caddy 단일 컨테이너)
- 백엔드 서버 없음. 순수 정적 SPA.

## 프로젝트 구조

```
portfolio/
├── settings.gradle.kts         # rootProject.name = "portfolio", webApp 모듈 등록
├── build.gradle.kts            # 공통 플러그인 버전 관리 (apply false)
├── gradle.properties           # Gradle / Kotlin-Wasm 빌드 옵션
├── gradle/
│   └── libs.versions.toml      # 버전 카탈로그 (Kotlin, Compose)
├── webApp/                     # Compose Multiplatform for Web 모듈
│   ├── build.gradle.kts        # wasmJs 타겟 및 Compose 의존성
│   └── src/wasmJsMain/
│       ├── kotlin/Main.kt      # 진입점 + 포트폴리오 UI
│       ├── composeResources/font/  # 한글 폰트(Pretendard) 리소스
│       └── resources/          # index.html, favicon 등 정적 자산
├── caddy/
│   └── Caddyfile               # 도메인/정적 서빙/SPA 라우팅 설정
├── docker-compose.yml          # Caddy 서비스 정의
└── README.md
```

## 로컬 빌드

```bash
# 프로덕션 정적 파일 생성
./gradlew :webApp:wasmJsBrowserDistribution

# 산출물 경로
# webApp/build/dist/wasmJs/productionExecutable/
```

로컬에서 확인하려면 산출물 디렉토리를 정적 서버로 서빙하면 됩니다. (WebGL을 지원하는 실제 브라우저에서 열어야 정상 렌더링됩니다.)

```bash
cd webApp/build/dist/wasmJs/productionExecutable && python3 -m http.server 8080
```

## 배포

```bash
# 1) 정적 파일 빌드 (배포 대상 서버 또는 CI 에서)
./gradlew :webApp:wasmJsBrowserDistribution

# 2) Caddy 컨테이너 기동
docker compose up -d
```

Caddy가 `jeonghoonlee.com` 에 대한 Let's Encrypt 인증서를 자동 발급하고, 만료 전 자동 갱신합니다. 별도의 인증서 스크립트는 필요하지 않습니다.

---

## ⚠️ 배포 전 체크리스트

실제 배포 전에 아래 항목을 반드시 직접 확인하세요.

- [ ] **DNS A 레코드**: `jeonghoonlee.com` 의 A 레코드가 배포 서버의 **공개 IP** 를 정확히 가리키는지 확인
      (Caddy 가 인증서를 발급하려면 도메인이 이 서버로 실제 연결되어야 합니다.)
- [ ] **포트 개방 (80 / 443)**:
    - 클라우드(EC2 등): 보안 그룹 / 방화벽에서 `80`, `443` 인바운드 허용
    - 홈서버: 공유기에서 `80`, `443` 포트포워딩 설정
      (특히 `80` 포트는 Let's Encrypt HTTP-01 챌린지에 필요합니다.)
- [ ] **정적 파일 빌드 완료**: `webApp/build/dist/wasmJs/productionExecutable` 에 최신 산출물이 존재하는지 확인
      (이 경로가 컨테이너의 `/srv/webapp` 로 마운트됩니다.)
- [ ] **인증서 볼륨 보존**: `caddy_data`, `caddy_config` 볼륨을 **절대 삭제하지 말 것**
      (`caddy_data` 에는 발급된 인증서와 ACME 계정 정보가 저장됩니다. 삭제 시 인증서가 재발급되며,
       짧은 시간 내 반복 발급하면 Let's Encrypt Rate Limit 에 걸릴 수 있습니다.)

---

## 폰트 라이선스

한글 표시를 위해 [Pretendard](https://github.com/orioncactus/pretendard) 폰트를 사용합니다. (SIL Open Font License 1.1)

## 커밋 컨벤션

Conventional Commits 규칙을 따릅니다. 자세한 내용은 `.gitmessage.txt` 를 참고하세요.

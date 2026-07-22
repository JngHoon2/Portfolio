// ====================================================================
// 포트폴리오 정적 사이트 CI/CD 파이프라인
// --------------------------------------------------------------------
// 정적 파일은 bind mount 가 아니라 docker build 로 이미지 안에 구워 넣는다.
// (Jenkins 가 컨테이너로 도는 환경에서 workspace 경로와 호스트 dockerd 경로가
//  어긋나 bind mount 가 깨지는 문제를 피하기 위함 — Dockerfile 참고)
//
// caddy_data / caddy_config 볼륨은 인증서 저장소이므로 파이프라인에서
// 절대 삭제(docker compose down -v 등)하지 않는다.
// ====================================================================

pipeline {
    agent any

    triggers {
        githubPush()
    }

    options {
        timestamps()
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: '20'))
    }

    stages {
        stage('build') {
            steps {
                echo '===== [START] 빌드 ====='
                script {
                    try {
                        sh 'chmod +x ./gradlew'
                        sh './gradlew :webApp:wasmJsBrowserDistribution --no-daemon'
                    } catch (Exception e) {
                        echo "===== [FAIL] 빌드 실패 ====="
                        echo "원인: ${e.getMessage()}"
                        throw e
                    }
                }
                echo '===== [SUCCESS] 빌드 성공 ====='
            }
        }

        stage('docker build') {
            steps {
                echo '===== [START] 도커 이미지 빌드 ====='
                script {
                    try {
                        sh 'docker build -t portfolio-web:latest .'
                    } catch (Exception e) {
                        echo "===== [FAIL] 도커 이미지 빌드 실패 ====="
                        echo "원인: ${e.getMessage()}"
                        throw e
                    }
                }
                echo '===== [SUCCESS] 도커 이미지 빌드 성공 ====='
            }
        }

        stage('deploy') {
            steps {
                echo '===== [START] 배포 ====='
                script {
                    try {
                        sh 'docker compose up -d'
                    } catch (Exception e) {
                        echo "===== [FAIL] 배포 실패 ====="
                        echo "원인: ${e.getMessage()}"
                        throw e
                    }
                }
                echo '===== [SUCCESS] 배포 성공 ====='
            }
        }
    }
}

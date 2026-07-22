# ====================================================================
# 포트폴리오 정적 사이트 이미지
# --------------------------------------------------------------------
# Gradle 빌드(webApp/build/dist/wasmJs/productionExecutable)는
# Jenkins 파이프라인의 별도 build 단계에서 미리 끝내고,
# 여기서는 그 결과물을 Caddy 이미지 안에 그대로 구워 넣기만 한다.
#
# bind mount 대신 이미지에 파일을 포함시키는 이유:
# Jenkins가 컨테이너로 떠 있을 때 workspace 경로와 호스트 dockerd가 보는
# 경로가 달라서 bind mount 가 어긋나는 문제가 있었는데, docker build는
# 빌드 컨텍스트를 파일 데이터로 통째로 전송하기 때문에 이 문제가 없다.
# ====================================================================
FROM caddy:2-alpine

COPY caddy/Caddyfile /etc/caddy/Caddyfile
COPY webApp/build/dist/wasmJs/productionExecutable /srv/webapp

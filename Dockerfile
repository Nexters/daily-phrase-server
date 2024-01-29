# 사용할 OpenJDK 베이스 이미지
FROM openjdk:17-jdk-slim as build

# 작업 디렉토리 설정
WORKDIR /app

# 소스 코드와 빌드 도구 복사
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

# 빌드 실행
RUN ./gradlew build -x test -Pprofile=dev

# 런타임 이미지 준비
FROM openjdk:17-jdk-slim

# 빌드 스테이지에서 생성된 JAR 파일 복사
COPY --from=build /app/build/libs/*.jar app.jar

# 애플리케이션 실행
ENTRYPOINT ["java","-jar","/app.jar"]

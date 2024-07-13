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

# 타임존 설정을 위해 tzdata 패키지 설치
RUN apt-get update && apt-get install -y tzdata

# 타임존 설정
ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 빌드 스테이지에서 생성된 JAR 파일 복사
COPY --from=build /app/build/libs/*.jar app.jar

# 애플리케이션 실행
ENTRYPOINT ["java","-jar","/app.jar"]

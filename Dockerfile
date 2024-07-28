FROM openjdk:17-jdk-slim

# 타임존 설정을 위해 tzdata 패키지 설치
RUN apt-get update && apt-get install -y tzdata

# 타임존 설정
ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 애플리케이션 JAR 파일 추가
ADD app.jar app.jar

# 프로파일 ARG 설정
ARG SPRING_PROFILES_ACTIVE
ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE}

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]

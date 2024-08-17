FROM openjdk:17-jdk-slim

# 필요한 패키지 설치
RUN apt-get update && apt-get install -y \
    tzdata \
    wget \
    iputils-ping && apt-get clean

# 타임존 설정
ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# Scouter 에이전트 다운로드 및 설치
RUN wget https://github.com/scouter-project/scouter/releases/download/v2.20.0/scouter-all-2.20.0.tar.gz \
    && tar zxvf scouter-all-2.20.0.tar.gz \
    && mkdir -p /opt/scouter-agent \
    && mv scouter/agent.java/* /opt/scouter-agent/ \
    && rm -rf scouter-all-2.20.0.tar.gz scouter

# Scouter 에이전트 설정 파일에 net_collector_ip 및 포트 설정 추가
ARG COLLECTOR_IP
RUN echo "net_collector_ip=${COLLECTOR_IP}" >> /opt/scouter-agent/conf/scouter.conf \
    && echo "net_collector_tcp_port=6100" >> /opt/scouter-agent/conf/scouter.conf \
    && echo "net_collector_udp_port=6100" >> /opt/scouter-agent/conf/scouter.conf

# 애플리케이션 JAR 파일 추가
ADD app.jar app.jar

# 프로파일 ARG 설정
ARG SPRING_PROFILES_ACTIVE
ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE}

# Scouter 에이전트 설정
ENV SCOUTER_AGENT_DIR=/opt/scouter-agent
ENV JAVA_OPTS="-javaagent:${SCOUTER_AGENT_DIR}/scouter.agent.jar -Dscouter.config=${SCOUTER_AGENT_DIR}/conf/scouter.conf"

# JAVA_OPTS를 포함하여 Java 애플리케이션 실행
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar"]

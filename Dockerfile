FROM maven:3.6.3-jdk-11 AS build
WORKDIR /app

COPY /home/user/.m2 /root/.m2

COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:11.0.14.1_1-jre-alpine
ENV TZ=Asia/Aqtau
RUN mkdir -p /apps/tmp
WORKDIR /apps

EXPOSE 9090

COPY --from=build /app/target/administration-*.jar /apps/administration.jar

RUN adduser --disabled-password -u 8835 sa && chown -R sa /apps/
USER sa

# Запуск приложения
ENTRYPOINT ["java", "-jar", "administration.jar"]
# Stage 1: Build the application with Maven
FROM maven:3.8.6-openjdk-11-slim AS build
WORKDIR /app

# Скопируем локально кэшированные зависимости и проект
COPY .m2 /root/.m2
COPY . .

# Соберем артефакт (уже с кэшированными зависимостями)
RUN mvn clean package -DskipTests

# Stage 2: Create the final image with just the JAR file
FROM openjdk:11-jdk-slim
WORKDIR /app

# Копируем JAR файл из первого этапа
COPY --from=build /app/target/administration-0.0.1-SNAPSHOT.jar /app/administration.jar

# Открываем порт
EXPOSE 9090

# Запуск приложения
ENTRYPOINT ["java", "-jar", "/app/administration.jar"]
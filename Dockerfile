# Stage 1: Build the application with Maven
FROM maven:3.8.6-openjdk-11-slim AS build
WORKDIR /app

# Скопируем файл pom.xml и загрузим зависимости
COPY pom.xml ./

# Указываем альтернативный репозиторий в случае проблем с центральным репозиторием
RUN mvn dependency:go-offline -B -Dmaven.repo.local=/root/.m2/repository -Drepo1.maven.org/maven2

# Скопируем весь проект и соберем артефакт
COPY . ./
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

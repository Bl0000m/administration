# Stage 1: Build the application with Maven
FROM maven:3.8.6-openjdk-11-slim AS build
WORKDIR /app

# Скопируем файл pom.xml и загрузим зависимости
COPY ./pom.xml ./

# Создадим каталог .m2 и добавим зеркала для Maven репозиториев
RUN mkdir -p /root/.m2 && echo "<settings><mirrors><mirror><id>central</id><mirrorOf>central</mirrorOf><url>https://repo1.maven.org/maven2/</url></mirror></mirrors></settings>" > /root/.m2/settings.xml

# Скопируем исходники проекта
COPY ./src ./src

# Собираем проект и скачиваем зависимости
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
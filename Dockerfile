# Скопируем файл pom.xml и загрузим зависимости
COPY pom.xml ./

# Загрузим зависимости без использования кэширования
RUN mvn dependency:resolve

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
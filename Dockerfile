FROM openjdk:11-jdk-slim
# workdir
WORKDIR /app

COPY pom.xml ./
RUN apt-get update && apt-get install -y maven
RUN mvn dependency:go-offline -B

COPY . ./

RUN mvn clean package -DskipTests

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "target/administration-0.0.1-SNAPSHOT.jar"]

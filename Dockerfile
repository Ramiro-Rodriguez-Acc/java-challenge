FROM eclipse-temurin:21.0.3_9-jdk-jammy AS builder
COPY .mvn/ .mvn/
COPY mvnw .
COPY pom.xml .
COPY . .
RUN sh mvnw clean package -DskipTests

FROM eclipse-temurin:21.0.3_9-jre-jammy
COPY --from=builder target/*.jar java-challenge-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","java-challenge-0.0.1-SNAPSHOT.jar"]
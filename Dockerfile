FROM eclipse-temurin:22-jre-alpine
WORKDIR /usr/src/app

COPY consumer/target/consumer-1.0-SNAPSHOT.jar ./consumer.jar
COPY service/target/service-1.0-SNAPSHOT.jar ./service.jar
COPY provider/target/provider-1.0-SNAPSHOT.jar ./provider.jar

ENTRYPOINT ["java", "--module-path","/usr/src/app", "--module","org.example.consumer/org.example.consumer.Main"]

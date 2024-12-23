FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/UDAAN_KAM1-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/kam
ENV SPRING_DATASOURCE_USERNAME=myuser
ENV SPRING_DATASOURCE_PASSWORD=pass

ENTRYPOINT ["java", "-jar", "app.jar"]

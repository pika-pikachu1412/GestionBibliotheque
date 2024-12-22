# Étape 1: Utiliser une image Maven avec OpenJDK 17 pour construire le projet
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml /app/pom.xml
COPY src /app/src
RUN mvn clean package -DskipTests

# Étape 2: Utiliser une image OpenJDK pour exécuter l'application
FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/GestionBibliotheque-1.0-SNAPSHOT.jar /app/GestionBibliotheque.jar

# Exposer le port si l'application en utilise un (optionnel)
# EXPOSE 8080

# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "GestionBibliotheque.jar"]

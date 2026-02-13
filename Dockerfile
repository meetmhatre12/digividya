# ---------- BUILD STAGE ----------
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

# ---------- RUN STAGE ----------
FROM tomcat:9.0-jdk17
COPY --from=build /app/target/DigiVidya.war /usr/local/tomcat/webapps/
EXPOSE 8080
